package com.blue.harvest.exception;

public class Error {

	public Error() {}
	
	public Error(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	private int code;
	
	private String message;

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
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
