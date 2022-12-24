package com.zensar.pm.panel.exceptions;

public class WrongDateException extends RuntimeException {

	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

	private String message;
	public WrongDateException(String message) {
		super();
		this.message = message;
	}
	



	
	
}
