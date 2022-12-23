package com.zensar.pm.panel.exceptions;

 

import org.springframework.web.bind.annotation.ResponseStatus;
public class InvalidAssociateNameException extends RuntimeException {
    private String errorMessage;

 

    public InvalidAssociateNameException() {
        super();
    }

 

    public InvalidAssociateNameException(String message) {
        super(message);
        this.errorMessage = message;
    }

 

    public String getErrorMessage() {
        return errorMessage;
    }
}
