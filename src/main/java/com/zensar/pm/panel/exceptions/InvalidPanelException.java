package com.zensar.pm.panel.exceptions;

public class InvalidPanelException extends RuntimeException {
	private String errorMessage;

	public InvalidPanelException() {
		super();
	}

	public InvalidPanelException(String message) {
		super(message);
		this.errorMessage = message;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}