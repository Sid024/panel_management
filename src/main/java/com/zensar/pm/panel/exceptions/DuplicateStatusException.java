package com.zensar.pm.panel.exceptions;

public class DuplicateStatusException extends RuntimeException{

	private String errorMessage;
	
	
	public DuplicateStatusException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}


	public DuplicateStatusException() {
		super();
	}
}
