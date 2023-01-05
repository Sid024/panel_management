package com.zensar.pm.panel.exceptions;

public class AssociateIdAlreadyExistsException extends RuntimeException{

	private String message;

	public AssociateIdAlreadyExistsException() {
		super();
	}

	public AssociateIdAlreadyExistsException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "AssociateIdAlreadyExistsException [message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	

	
	
}
