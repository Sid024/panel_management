package com.zensar.pm.panel.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class DateAlreadyExistsException extends RuntimeException{
	private String msg;

	public DateAlreadyExistsException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
}
