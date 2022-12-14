package com.blue.harvest.api.exception;

import com.blue.harvest.api.beans.Error;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final Error error;
		
    public BusinessException(int code, String message) {
    	super();
    	this.error = new Error(code, message);
        
    }

	/**
	 * @return the error
	 */
	public Error getError() {
		return error;
	}

}