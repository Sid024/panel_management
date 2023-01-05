package com.zensar.pm.panel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class UserNotFoundException extends RuntimeException {

	private String message;

	public UserNotFoundException(String message) {
		super();
		this.message = message;
	}

	public UserNotFoundException() {
		super();
	}

	@Override
	public String toString() {
		return "UserNotFoundException [message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
