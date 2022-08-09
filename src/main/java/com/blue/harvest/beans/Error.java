package com.blue.harvest.beans;

import java.io.Serializable;

public class Error implements Serializable {

	private static final long serialVersionUID = 1L;

	public Error(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	private int code;
	
	private String message;

	/**
	 * @return the error code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the error code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
