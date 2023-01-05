package com.zensar.pm.panel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class PanelNotFound extends RuntimeException {

	private String message;

	public PanelNotFound(String message) {
		super();
		this.message = message;
	}

	public PanelNotFound() {
		super();
	}

	@Override
	public String toString() {
		return "PanelNotFound [message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
