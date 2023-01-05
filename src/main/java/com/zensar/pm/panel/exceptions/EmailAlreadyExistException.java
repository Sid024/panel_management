package com.zensar.pm.panel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistException extends RuntimeException{
	
	private String message;

	public EmailAlreadyExistException(String message) {
		super();
		this.message = message;
	}

	public EmailAlreadyExistException() {
		super();
	}

	@Override
	public String toString() {
		return "EmailAlreadyExistException [message=" + message + "]";
	}
	
	

}
