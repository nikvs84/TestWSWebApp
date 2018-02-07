package com.forward.exchange.webservices.kit;

import java.io.Serializable;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

public class SuzaWebRequestSQL implements SQLData, Serializable {
	public static final String TYPE_NAME = "T_SUZA_WEB_REQUEST";

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Integer userId;
	private String webMethod;
	private Integer requestNum;

	@Override
	public String getSQLTypeName() throws SQLException {
		return TYPE_NAME;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getWebMethod() {
		return webMethod;
	}

	public void setWebMethod(String webMethod) {
		this.webMethod = webMethod;
	}

	public Integer getRequestNum() {
		return requestNum;
	}

	public void setRequestNum(Integer requestNum) {
		this.requestNum = requestNum;
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		userId = stream.readInt();
		webMethod = stream.readString();
		requestNum = stream.readInt();
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeInt(userId);
		stream.writeString(webMethod);
		stream.writeInt(requestNum);
	}

	@Override
	public String toString() {
		return "SuzaWebRequestSQL{" +
				"userId=" + userId +
				", webMethod='" + webMethod + '\'' +
				", requestNum=" + requestNum +
				'}';
	}


}
