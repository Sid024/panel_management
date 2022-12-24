package com.zensar.pm.panel.exceptions;

public class EmptyListException extends RuntimeException {
	private String errorMessage;

	public EmptyListException() {
		super();
	}

	public EmptyListException(String message) {
		super(message);
		this.errorMessage = message;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
