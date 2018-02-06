package com.forward.exchange.webservices.kit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DatabaseExchange {
    private static final AtomicInteger requestNumber = new AtomicInteger(0);

    /**
     * Кодировка в UTF-8
     */
    public static final String DEFAULT_UTF_ENCODING = "UTF-8";

    /**
     * Наименование источника данных SQL (JNDI имя)
     */
    public static final String DBMS_DATASOURCE = "billingDataSource";

    /**
     * Разделитель
     */
    public static final String PROCEDURE_NAME_DELIMITER = "@";

    /**
     * Именованные выходные параметры в хранимой процедуре БД
     */
    private static final String REST_API_JSON_RESPONSE = "pRESPONSE_JSON";
    private static final String REST_API_JSON_REQUEST = "pREQUEST_JSON";
    private static final String REST_API_CALL_CONTEXT = "pCONTEXT";

    private DataSource dataSource;

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseExchange.class);

    public static AtomicInteger getRequestNumber() {
        return requestNumber;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Получение валидатора бинов
     */
    private static final ThreadLocal<Validator> VALIDATORS = new ThreadLocal<Validator>() {
        @Override
        protected Validator initialValue() {
            Validator beanValidator = null;

            beanValidator = Validation.buildDefaultValidatorFactory().getValidator();
            LOGGER.info("Bean validation is enabled. Using validator: '{}' in thread '{}'.",
                    beanValidator.getClass(), Thread.currentThread().getName());

            return beanValidator;
        }
    };

    /**
     * Метод валидирует полученный бин
     *
     * @param bean
     */
    private void validateBean(final int requestNum, Object bean) {
        Validator validator = VALIDATORS.get();
        if (validator == null) {
            LOGGER.debug("[{}] Bean validation is disabled", requestNum);
            return;//---
        }
        StringBuilder sb = new StringBuilder();
        validator.validate(bean).forEach(c -> {
            if (sb.length() > 0) {
                sb.append("; ");
            }
            sb.append(c.getPropertyPath().toString()).append(" ").append(c.getMessage());
        });

        if (sb.length() == 0) {
            LOGGER.debug("[{}] Bean {} is valid", requestNum, bean.getClass());
            return;//---
        }

        LOGGER.warn("[{}] Bean {} is not valid", requestNum, bean.getClass());
        throw new javax.validation.ValidationException(sb.append(".").toString());
    }


    /**
     * Инициализация dataSource
     */
    private void initDataSource() {
        if (this.getDataSource() == null) {
            try {
                InitialContext initialContext = new InitialContext();
                DataSource dataSource = (DataSource) initialContext.lookup(DBMS_DATASOURCE);
                this.setDataSource(dataSource);
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод возвращает уникальный код запроса
     */
    public int getRequestId() {
        int requestId = requestNumber.incrementAndGet();
        LOGGER.trace("[{}] Next request id received", requestId);
        return requestId;
    }

    /**
     * Метод выполняет общую обработку запроса вебсервиса
     *
     * @param methodName
     * @return
     */
    public Response processJsonRequest(Object bean, ContainerRequestContext requestContext, String methodName) {
        final int requestNum = this.getRequestId();

        try {
            LOGGER.info("[{}] >>> Request {}", requestNum, methodName);

            String jsonResponse = this.processJsonRequestDirect(requestNum, bean, requestContext, methodName);

            //Отлогируем возврат
            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace("[{}] <<< Response: {}", requestNum, jsonResponse);
            } else {
                LOGGER.info("[{}] <<< Response", requestNum);
            }

            return Response.ok().entity(new ByteArrayInputStream(jsonResponse.getBytes())).build();
        } catch (Exception e) {
            return this.processRestApiError(requestNum, e);
        }
    }

    private Response processRestApiError(int requestNum, Exception e) {
        String jsonResponse = "";
        try {
            jsonResponse = new ObjectMapper().writeValueAsString(e.getMessage());
        } catch (JsonProcessingException e1) {
            e1.printStackTrace();
        }
        return Response.serverError().entity(jsonResponse).build();
    }

    /**
     * Метод вычитывает json из запроса и выполняет в БД
     */
    public String processJsonRequestDirect(final int requestNum, Object bean, ContainerRequestContext requestContext,
                                           String methodName) throws Exception {
		/*
			Валидируем полученный bean
		 */
        this.validateBean(requestNum, bean);

		/*
			Вычитаем JSON из запроса
		 */
        InputStream is = requestContext.getEntityStream();
        is.reset();
        final String jsonRequest = IOUtils.toString(is, DEFAULT_UTF_ENCODING);

        //Отлогируем взвлечение
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("[{}] Json readed from request: {}", requestNum, jsonRequest);
        } else {
            LOGGER.debug("[{}] Json readed from request", requestNum);
        }

        /*
         * Вызываем БД
         */
        return this.processJsonRequestDb(requestNum, jsonRequest, methodName);
    }

    private String processJsonRequestDb(int requestNum, String jsonRequest, String methodName) {
        initDataSource();
        String procedureName = getProcedureName(methodName);
        String sql = "{call " + procedureName + "(?,?)}";
        try (Connection connection = dataSource.getConnection()) {
            CallableStatement cstmt = connection.prepareCall(sql);

            Clob jsonRequestClob = dataSource.getConnection().createClob();
            jsonRequestClob.setString(1, jsonRequest);
            cstmt.setClob(REST_API_JSON_REQUEST, jsonRequestClob);
            cstmt.registerOutParameter(REST_API_JSON_RESPONSE, Types.CLOB);

            cstmt.execute();

            Clob result = cstmt.getClob(REST_API_JSON_RESPONSE);
            return result.getSubString(1, (int) result.length());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String testRequestDB() {
        StringBuilder result = new StringBuilder();
        String sql = "SELECT * FROM FW_VERSION_PROJECT";
        initDataSource();
        try (Connection connection = this.dataSource.getConnection()) {
            Statement cstmt = connection.createStatement();
            try (ResultSet rs = cstmt.executeQuery(sql)) {
                while (rs.next()) {
                    result.append("Version:" + System.getProperty("line.separator"));
                    result.append("\tV_PROJECT:\t\t\t\t" + rs.getString(1) + System.getProperty("line.separator"));
                    result.append("\tN_DATABASE_VER:\t\t" + rs.getInt(2) + System.getProperty("line.separator"));
                    result.append("\tV_VERSION_STAGE:\t\t" + rs.getString(3) + System.getProperty("line.separator"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    public String testCallProcDB(String jsonRequest, String methodName) {
        StringBuilder result = new StringBuilder();
        String sql = "call " + getProcedureName(methodName) + "(?, ?)";
        initDataSource();
        try (Connection connection = this.dataSource.getConnection()) {
            CallableStatement cstmt = connection.prepareCall(sql);
            cstmt.setString(REST_API_JSON_REQUEST, jsonRequest);
            cstmt.registerOutParameter(REST_API_JSON_RESPONSE, Types.CLOB);
            cstmt.execute();
            Clob resultClob = cstmt.getClob(REST_API_JSON_RESPONSE);
            result.append(resultClob.getSubString(1, (int) resultClob.length()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    public String testCallProcDB(Object bean, String methodName) {
        try {
            String request = new ObjectMapper().writeValueAsString(bean);
            int requestNum = getRequestId();
            validateBean(requestNum, bean);
            return testCallProcDB(request, methodName);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Метод прямой обработки запроса в БД
     */
/*
        public String processJsonRequestDb(final int requestNum, String jsonRequest,
                                       String methodName) throws Exception {

		*//*
			Должны получить валидный json
		 *//*
        if (jsonRequest == null) {
            throw new IllegalArgumentException("Json is null");
        }

        //TODO - выкинуть
        this.initDataSource();

		*//*
			Определим процедуру, которую будем вызывать
		 *//*
        final String procedure = this.getProcedureName(methodName);
        LOGGER.debug("[{}] Method '{}' is db procedure: {}", requestNum, methodName, procedure);

		*//*
			Подготовим контекст вызова
		 *//*
        final TcsWebRequestSQL context = new TcsWebRequestSQL();
        context.setManagerId(this.getUser());
        context.setWebMethod(methodName);
        context.setRequestNum(requestNum);

		*//*
			Выполняем запрос в БД
		*//*
        try {
            LOGGER.debug("[{}] {}", requestNum, context);
            return this.executeOnDbImpl(requestNum, procedure, jsonRequest, context);
        } catch (SQLException sqlEx) {
            LOGGER.warn("[{}] Sql error received: {}", requestNum, sqlEx.getErrorCode());
            if (DBQueryLite.isPackageStateInvalid(sqlEx)) {
                LOGGER.warn("[{}] Resend {}", requestNum, context);
                return this.executeOnDbImpl(requestNum, procedure, jsonRequest, context);
            }
            throw sqlEx;
        }
    }
*/
    private String getProcedureName(String methodName) {
        String result = "";

//        String packageName = this.getClass().getPackage().getName();
//        result =  packageName + PROCEDURE_NAME_DELIMITER + methodName;

        result = "TEST_TRASH_PACK." + methodName;

        return result;
    }

}
