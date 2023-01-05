package com.zensar.pm.panel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class GradeNotFoundException extends RuntimeException{
	
	private String message;

	public GradeNotFoundException(String message) {
		super();
		this.message = message;
	}

	public GradeNotFoundException() {
		super();
	}

	@Override
	public String toString() {
		return "GradeNotFoundException [message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
