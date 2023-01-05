package com.zensar.pm.panel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class MailNotSentException extends RuntimeException {

	public MailNotSentException(String msg) {
		super(msg);
	}
}
