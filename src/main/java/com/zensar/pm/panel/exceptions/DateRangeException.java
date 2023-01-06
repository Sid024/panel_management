package com.zensar.pm.panel.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateRangeException extends RuntimeException{

	private String msg;
	
	public DateRangeException(String msg) {
		this.msg=msg;
	}
}
