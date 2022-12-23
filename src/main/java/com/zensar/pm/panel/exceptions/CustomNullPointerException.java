package com.zensar.pm.panel.exceptions;

public class CustomNullPointerException extends RuntimeException {

	private String message;

	public CustomNullPointerException() {
		super();
	}

	public CustomNullPointerException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
