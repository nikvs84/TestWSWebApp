package com.forward.exchange.webservices.kit;

import java.io.Serializable;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

public class WebRequestSQL implements SQLData, Serializable {
	public static final String TYPE_NAME = "T_TEST_TRASH_WS";

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Integer managerId;
	private String webMethod;
	private Integer requestNum;

	@Override
	public String getSQLTypeName() throws SQLException {
		return TYPE_NAME;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
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
		managerId = stream.readInt();
		webMethod = stream.readString();
		requestNum = stream.readInt();
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeInt(managerId);
		stream.writeString(webMethod);
		stream.writeInt(requestNum);
	}

	@Override
	public String toString() {
		return "WebRequestSQL{" +
				"managerId=" + managerId +
				", webMethod='" + webMethod + '\'' +
				", requestNum=" + requestNum +
				'}';
	}


}
