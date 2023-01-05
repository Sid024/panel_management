package com.zensar.pm.panel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class NoSuchRoleFoundException  extends RuntimeException{
	
	private String message;

	public NoSuchRoleFoundException(String message) {
		super();
		this.message = message;
	}

	public NoSuchRoleFoundException() {
		super();
	}

	@Override
	public String toString() {
		return "NoSuchRoleFoundException [message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
