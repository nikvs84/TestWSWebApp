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
import com.forward.exchange.webservices.suza.model.CommentList;
import com.forward.exchange.webservices.suza.model.FileList;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.*;
import javax.validation.constraints.*;

/**
 * GetRequestCommentsResponse
 */

public class GetRequestCommentsResponse   {
  @JsonProperty("RESULT_CODE")
  @NotNull
  private Integer RESULT_CODE = null;

  @JsonProperty("MESSAGE")
  private String MESSAGE = null;

  @JsonProperty("COMMENT_LIST")
  @Valid
  private List<CommentList> COMMENT_LIST = null;

  @JsonProperty("FILE_LIST")
  @Valid
  private List<FileList> FILE_LIST = null;

  public GetRequestCommentsResponse RESULT_CODE(Integer RESULT_CODE) {
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

  public GetRequestCommentsResponse MESSAGE(String MESSAGE) {
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

  public GetRequestCommentsResponse COMMENT_LIST(List<CommentList> COMMENT_LIST) {
    this.COMMENT_LIST = COMMENT_LIST;
    return this;
  }

  public GetRequestCommentsResponse addCOMMENTLISTItem(CommentList COMMENT_LISTItem) {
    if (this.COMMENT_LIST == null) {
      this.COMMENT_LIST = new ArrayList<CommentList>();
    }
    this.COMMENT_LIST.add(COMMENT_LISTItem);
    return this;
  }

  /**
   * Список комментариев
   * @return COMMENT_LIST
   **/
  @JsonProperty("COMMENT_LIST")
  @ApiModelProperty(value = "Список комментариев")
  public List<CommentList> getCOMMENTLIST() {
    return COMMENT_LIST;
  }

  public void setCOMMENTLIST(List<CommentList> COMMENT_LIST) {
    this.COMMENT_LIST = COMMENT_LIST;
  }

  public GetRequestCommentsResponse FILE_LIST(List<FileList> FILE_LIST) {
    this.FILE_LIST = FILE_LIST;
    return this;
  }

  public GetRequestCommentsResponse addFILELISTItem(FileList FILE_LISTItem) {
    if (this.FILE_LIST == null) {
      this.FILE_LIST = new ArrayList<FileList>();
    }
    this.FILE_LIST.add(FILE_LISTItem);
    return this;
  }

  /**
   * Список комментариев
   * @return FILE_LIST
   **/
  @JsonProperty("FILE_LIST")
  @ApiModelProperty(value = "Список комментариев")
  public List<FileList> getFILELIST() {
    return FILE_LIST;
  }

  public void setFILELIST(List<FileList> FILE_LIST) {
    this.FILE_LIST = FILE_LIST;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetRequestCommentsResponse getRequestCommentsResponse = (GetRequestCommentsResponse) o;
    return Objects.equals(this.RESULT_CODE, getRequestCommentsResponse.RESULT_CODE) &&
        Objects.equals(this.MESSAGE, getRequestCommentsResponse.MESSAGE) &&
        Objects.equals(this.COMMENT_LIST, getRequestCommentsResponse.COMMENT_LIST) &&
        Objects.equals(this.FILE_LIST, getRequestCommentsResponse.FILE_LIST);
  }

  @Override
  public int hashCode() {
    return Objects.hash(RESULT_CODE, MESSAGE, COMMENT_LIST, FILE_LIST);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetRequestCommentsResponse {\n");
    
    sb.append("    RESULT_CODE: ").append(toIndentedString(RESULT_CODE)).append("\n");
    sb.append("    MESSAGE: ").append(toIndentedString(MESSAGE)).append("\n");
    sb.append("    COMMENT_LIST: ").append(toIndentedString(COMMENT_LIST)).append("\n");
    sb.append("    FILE_LIST: ").append(toIndentedString(FILE_LIST)).append("\n");
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
