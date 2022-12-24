package com.zensar.pm.panel.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class UnauthorizedUserException extends RuntimeException{
	private String error;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public UnauthorizedUserException(String error) {
		super();
		this.error = error;
	}

	public UnauthorizedUserException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UnauthorizedUserException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UnauthorizedUserException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UnauthorizedUserException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
