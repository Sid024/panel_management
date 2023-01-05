package com.zensar.pm.panel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class PanelAlreadyExists extends RuntimeException{
	private String message;

	public PanelAlreadyExists() {
		super();
	}

	public PanelAlreadyExists(String message) {
		super(message);
		this.message = message;
	}



}
