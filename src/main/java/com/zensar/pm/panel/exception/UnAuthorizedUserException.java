package com.zensar.pm.panel.exception;

public class UnAuthorizedUserException   extends RuntimeException {
	
	private String error;
	public UnAuthorizedUserException() {
		super();

	}	

	public UnAuthorizedUserException(String error) {
		super();
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
