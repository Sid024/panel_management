package com.zensar.pm.panel.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class TimeFormatException extends RuntimeException{
	
	private String message;
	
	public TimeFormatException(String message) {
		this.message=message;
	}


}
