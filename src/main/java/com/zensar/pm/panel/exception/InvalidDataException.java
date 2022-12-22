package com.zensar.pm.panel.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidDataException extends RuntimeException {

	private String message;

	public InvalidDataException(String message) {
		super(message);
		this.message = message;
		// TODO Auto-generated constructor stub
	}

}
