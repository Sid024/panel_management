package com.zensar.pm.panel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class PanelCandidateRoleNotFoundException extends RuntimeException{
	
	private String message;

	public PanelCandidateRoleNotFoundException(String message) {
		super();
		this.message = message;
	}

	public PanelCandidateRoleNotFoundException() {
		super();
	}

	@Override
	public String toString() {
		return "PanelCandidateRoleNotFoundException [message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}


