package com.forward.exchange.webservices.kit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
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
    public static final String DB_PACKAGE_NAME = "ESUZA_WS_RESTful";

    private DataSource dataSource;

    @Context
    SecurityContext securityContext;

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
     * Метод вызова процедуры в БД
     * @param jsonRequest JSON
     * @param methodName название процедуры в БД
     * @param requestNum код запроса
     * @return JSON
     */
    public String callProcDB(String jsonRequest, String methodName, int requestNum) {
        String result = null;
        String sql = "call " + getProcedureName(methodName) + "(?, ?, ?)";
        initDataSource();
        try (Connection connection = this.dataSource.getConnection()) {

            SuzaWebRequestSQL context = new SuzaWebRequestSQL();
            context.setUserId(getUserId(securityContext));
            context.setWebMethod(methodName);
            context.setRequestNum(requestNum);

            CallableStatement cstmt = connection.prepareCall(sql);
            cstmt.setString(REST_API_JSON_REQUEST, jsonRequest);
            cstmt.setObject(REST_API_CALL_CONTEXT, context);
            cstmt.registerOutParameter(REST_API_JSON_RESPONSE, Types.CLOB);
            cstmt.execute();
            Clob resultClob = cstmt.getClob(REST_API_JSON_RESPONSE);
            result = resultClob.getSubString(1, (int) resultClob.length());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    /**
     * Метод прямой обработки запроса в БД
     */
    public Response execute(String jsonRequest, Class<?> clazz, String methodName) {
        int requestNum = getRequestId();

        //Отлогируем взвлечение
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("[{}] Json readed from request: {}", requestNum, jsonRequest);
        } else {
            LOGGER.debug("[{}] Json readed from request", requestNum);
        }
        try {
            Object bean = new ObjectMapper().readValue(jsonRequest, clazz);
            validateBean(requestNum, bean);

            String jsonResponse = this.callProcDB(jsonRequest, methodName, requestNum);

            //Отлогируем возврат
            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace("[{}] <<< Response: {}", requestNum, jsonResponse);
            } else {
                LOGGER.info("[{}] <<< Response", requestNum);
            }

            return Response.ok().entity(jsonResponse).build();
        } catch (Exception e) {
            return this.processRestApiError(requestNum, e);
        }
    }


    private String getProcedureName(String methodName) {
        String result = "";
        result = DB_PACKAGE_NAME + "." + methodName;

//        String packageName = this.getClass().getPackage().getName();
//        result =  packageName + PROCEDURE_NAME_DELIMITER + methodName;

        return result;
    }

    private int getUserId(SecurityContext securityContext) throws SQLException {
        String userName = securityContext.getUserPrincipal().getName();
        return getUserId(userName);
    }

    /**
     * Получение кода пользователя
     *
     * @param username
     * @return
     * @throws SQLException
     */
    private int getUserId(String username) throws SQLException {
        assert username != null : "query cannot be empty";
        initDataSource();
        try (Connection connection = dataSource.getConnection()) {
            String sql = "select ID_USER from CI_USERS where V_USERNAME = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                LOGGER.trace("User {} has ID = {}", username, id);
                return id;
            } else {
                throw new SQLException("Username '" + username + "' is unknown.");
            }
        }
    }

}
