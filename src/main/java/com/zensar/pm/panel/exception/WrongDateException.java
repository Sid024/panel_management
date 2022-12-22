package com.zensar.pm.panel.exception;

public class WrongDateException extends RuntimeException {

	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public WrongDateException(String message) {
		super();
		this.message = message;
	}


	
	
}
