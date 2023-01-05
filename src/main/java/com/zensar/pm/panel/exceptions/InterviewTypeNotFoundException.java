package com.zensar.pm.panel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class InterviewTypeNotFoundException extends RuntimeException{
	
	private String message;

	public InterviewTypeNotFoundException(String message) {
		super();
		this.message = message;
	}

	public InterviewTypeNotFoundException() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "InterviewTypeNotFoundException [message=" + message + "]";
	}
	
	

}
