package com.zensar.pm.panel.exceptions;

public class MatchNotFoundException extends RuntimeException {
	private String message;

	public MatchNotFoundException() {
		super();
	}

	public MatchNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
