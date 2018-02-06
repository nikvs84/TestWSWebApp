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


package com.forward.exchange.webservices.suza.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.*;
import javax.validation.constraints.*;

/**
 * CreateCommentResponse
 */

public class CreateCommentResponse   {
  @JsonProperty("RESULT_CODE")
  @NotNull
  private Integer RESULT_CODE = null;

  @JsonProperty("MESSAGE")
  private String MESSAGE = null;

  public CreateCommentResponse RESULT_CODE(Integer RESULT_CODE) {
    this.RESULT_CODE = RESULT_CODE;
    return this;
  }

  /**
   * Код результата выполнения запроса. Если успешно, то «0»
   * @return RESULT_CODE
   **/
  @JsonProperty("RESULT_CODE")
  @ApiModelProperty(required = true, value = "Код результата выполнения запроса. Если успешно, то «0»")
  public Integer getRESULTCODE() {
    return RESULT_CODE;
  }

  public void setRESULTCODE(Integer RESULT_CODE) {
    this.RESULT_CODE = RESULT_CODE;
  }

  public CreateCommentResponse MESSAGE(String MESSAGE) {
    this.MESSAGE = MESSAGE;
    return this;
  }

  /**
   * Сообщение о результате выполнения запроса. Если успешно, то не заполняется.
   * @return MESSAGE
   **/
  @JsonProperty("MESSAGE")
  @ApiModelProperty(value = "Сообщение о результате выполнения запроса. Если успешно, то не заполняется.")
  public String getMESSAGE() {
    return MESSAGE;
  }

  public void setMESSAGE(String MESSAGE) {
    this.MESSAGE = MESSAGE;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateCommentResponse createCommentResponse = (CreateCommentResponse) o;
    return Objects.equals(this.RESULT_CODE, createCommentResponse.RESULT_CODE) &&
        Objects.equals(this.MESSAGE, createCommentResponse.MESSAGE);
  }

  @Override
  public int hashCode() {
    return Objects.hash(RESULT_CODE, MESSAGE);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateCommentResponse {\n");
    
    sb.append("    RESULT_CODE: ").append(toIndentedString(RESULT_CODE)).append("\n");
    sb.append("    MESSAGE: ").append(toIndentedString(MESSAGE)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}