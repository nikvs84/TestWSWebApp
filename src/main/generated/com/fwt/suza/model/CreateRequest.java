/*
 * FW-Telecom WebServices
 * 1.  Интеграционные методы
 *
 * OpenAPI spec version: 1.0.0
 * Contact: apiteam@swagger.io
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.fwt.suza.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.*;
import javax.validation.constraints.*;

/**
 * CreateRequest
 */

public class CreateRequest   {
    @JsonProperty("V_REQUESTOR")
    @NotNull
    @Size(max=255)
    private String V_REQUESTOR = null;

    @JsonProperty("V_TITLE")
    @NotNull
    @Size(max=255)
    private String V_TITLE = null;

    @JsonProperty("V_DESCRIPTION")
    @NotNull
    @Size(min=1,max=4000)
    private String V_DESCRIPTION = null;

    @JsonProperty("V_EMAIL")
    @NotNull
    @Size(min=1,max=255)
    private String V_EMAIL = null;

    @JsonProperty("V_CIRCUIT")
    private String V_CIRCUIT = null;

    @JsonProperty("N_TYPE")
    @NotNull
    private Integer N_TYPE = null;

    @JsonProperty("N_PRIORITY")
    private Integer N_PRIORITY = null;

    @JsonProperty("N_LEVEL")
    private Integer N_LEVEL = null;

    @JsonProperty("V_EXT_IDENT")
    private String V_EXT_IDENT = null;

    @JsonProperty("FILE")
    private byte[] FILE = null;

    public CreateRequest V_REQUESTOR(String V_REQUESTOR) {
        this.V_REQUESTOR = V_REQUESTOR;
        return this;
    }

    /**
     * ФИО инициатора обращения
     * @return V_REQUESTOR
     **/
    @JsonProperty("V_REQUESTOR")
    @ApiModelProperty(required = true, value = "ФИО инициатора обращения")
    public String getVREQUESTOR() {
        return V_REQUESTOR;
    }

    public void setVREQUESTOR(String V_REQUESTOR) {
        this.V_REQUESTOR = V_REQUESTOR;
    }

    public CreateRequest V_TITLE(String V_TITLE) {
        this.V_TITLE = V_TITLE;
        return this;
    }

    /**
     * Тема обращения
     * @return V_TITLE
     **/
    @JsonProperty("V_TITLE")
    @ApiModelProperty(required = true, value = "Тема обращения")
    public String getVTITLE() {
        return V_TITLE;
    }

    public void setVTITLE(String V_TITLE) {
        this.V_TITLE = V_TITLE;
    }

    public CreateRequest V_DESCRIPTION(String V_DESCRIPTION) {
        this.V_DESCRIPTION = V_DESCRIPTION;
        return this;
    }

    /**
     * Полный текст обращения
     * @return V_DESCRIPTION
     **/
    @JsonProperty("V_DESCRIPTION")
    @ApiModelProperty(required = true, value = "Полный текст обращения")
    public String getVDESCRIPTION() {
        return V_DESCRIPTION;
    }

    public void setVDESCRIPTION(String V_DESCRIPTION) {
        this.V_DESCRIPTION = V_DESCRIPTION;
    }

    public CreateRequest V_EMAIL(String V_EMAIL) {
        this.V_EMAIL = V_EMAIL;
        return this;
    }

    /**
     * email инициатора обращения
     * @return V_EMAIL
     **/
    @JsonProperty("V_EMAIL")
    @ApiModelProperty(required = true, value = "email инициатора обращения")
    public String getVEMAIL() {
        return V_EMAIL;
    }

    public void setVEMAIL(String V_EMAIL) {
        this.V_EMAIL = V_EMAIL;
    }

    public CreateRequest V_CIRCUIT(String V_CIRCUIT) {
        this.V_CIRCUIT = V_CIRCUIT;
        return this;
    }

    /**
     * Контур(прод/тест)
     * @return V_CIRCUIT
     **/
    @JsonProperty("V_CIRCUIT")
    @ApiModelProperty(value = "Контур(прод/тест)")
    public String getVCIRCUIT() {
        return V_CIRCUIT;
    }

    public void setVCIRCUIT(String V_CIRCUIT) {
        this.V_CIRCUIT = V_CIRCUIT;
    }

    public CreateRequest N_TYPE(Integer N_TYPE) {
        this.N_TYPE = N_TYPE;
        return this;
    }

    /**
     * Get N_TYPE
     * @return N_TYPE
     **/
    @JsonProperty("N_TYPE")
    @ApiModelProperty(required = true, value = "")
    public Integer getNTYPE() {
        return N_TYPE;
    }

    public void setNTYPE(Integer N_TYPE) {
        this.N_TYPE = N_TYPE;
    }

    public CreateRequest N_PRIORITY(Integer N_PRIORITY) {
        this.N_PRIORITY = N_PRIORITY;
        return this;
    }

    /**
     * Уровень инцидента(N_TYPE&#x3D;0). Для остальных типов не обрабатывается. Чем меньше, тем выше
     * @return N_PRIORITY
     **/
    @JsonProperty("N_PRIORITY")
    @ApiModelProperty(value = "Уровень инцидента(N_TYPE=0). Для остальных типов не обрабатывается. Чем меньше, тем выше")
    public Integer getNPRIORITY() {
        return N_PRIORITY;
    }

    public void setNPRIORITY(Integer N_PRIORITY) {
        this.N_PRIORITY = N_PRIORITY;
    }

    public CreateRequest N_LEVEL(Integer N_LEVEL) {
        this.N_LEVEL = N_LEVEL;
        return this;
    }

    /**
     * Приоритет обращения. Чем больше, тем выше.
     * @return N_LEVEL
     **/
    @JsonProperty("N_LEVEL")
    @ApiModelProperty(value = "Приоритет обращения. Чем больше, тем выше.")
    public Integer getNLEVEL() {
        return N_LEVEL;
    }

    public void setNLEVEL(Integer N_LEVEL) {
        this.N_LEVEL = N_LEVEL;
    }

    public CreateRequest V_EXT_IDENT(String V_EXT_IDENT) {
        this.V_EXT_IDENT = V_EXT_IDENT;
        return this;
    }

    /**
     * Номер во внешнем трекере
     * @return V_EXT_IDENT
     **/
    @JsonProperty("V_EXT_IDENT")
    @ApiModelProperty(value = "Номер во внешнем трекере")
    public String getVEXTIDENT() {
        return V_EXT_IDENT;
    }

    public void setVEXTIDENT(String V_EXT_IDENT) {
        this.V_EXT_IDENT = V_EXT_IDENT;
    }

    public CreateRequest FILE(byte[] FILE) {
        this.FILE = FILE;
        return this;
    }

    /**
     * base64 файл
     * @return FILE
     **/
    @JsonProperty("FILE")
    @ApiModelProperty(value = "base64 файл")
    public byte[] getFILE() {
        return FILE;
    }

    public void setFILE(byte[] FILE) {
        this.FILE = FILE;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateRequest createRequest = (CreateRequest) o;
        return Objects.equals(this.V_REQUESTOR, createRequest.V_REQUESTOR) &&
                Objects.equals(this.V_TITLE, createRequest.V_TITLE) &&
                Objects.equals(this.V_DESCRIPTION, createRequest.V_DESCRIPTION) &&
                Objects.equals(this.V_EMAIL, createRequest.V_EMAIL) &&
                Objects.equals(this.V_CIRCUIT, createRequest.V_CIRCUIT) &&
                Objects.equals(this.N_TYPE, createRequest.N_TYPE) &&
                Objects.equals(this.N_PRIORITY, createRequest.N_PRIORITY) &&
                Objects.equals(this.N_LEVEL, createRequest.N_LEVEL) &&
                Objects.equals(this.V_EXT_IDENT, createRequest.V_EXT_IDENT) &&
                Objects.equals(this.FILE, createRequest.FILE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(V_REQUESTOR, V_TITLE, V_DESCRIPTION, V_EMAIL, V_CIRCUIT, N_TYPE, N_PRIORITY, N_LEVEL, V_EXT_IDENT, FILE);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateRequest {\n");

        sb.append("    V_REQUESTOR: ").append(toIndentedString(V_REQUESTOR)).append("\n");
        sb.append("    V_TITLE: ").append(toIndentedString(V_TITLE)).append("\n");
        sb.append("    V_DESCRIPTION: ").append(toIndentedString(V_DESCRIPTION)).append("\n");
        sb.append("    V_EMAIL: ").append(toIndentedString(V_EMAIL)).append("\n");
        sb.append("    V_CIRCUIT: ").append(toIndentedString(V_CIRCUIT)).append("\n");
        sb.append("    N_TYPE: ").append(toIndentedString(N_TYPE)).append("\n");
        sb.append("    N_PRIORITY: ").append(toIndentedString(N_PRIORITY)).append("\n");
        sb.append("    N_LEVEL: ").append(toIndentedString(N_LEVEL)).append("\n");
        sb.append("    V_EXT_IDENT: ").append(toIndentedString(V_EXT_IDENT)).append("\n");
        sb.append("    FILE: ").append(toIndentedString(FILE)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}