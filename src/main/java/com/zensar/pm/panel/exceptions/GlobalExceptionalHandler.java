package com.zensar.pm.panel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionalHandler extends RuntimeException {
	
	@ExceptionHandler(value = InvalidPanelException.class)
	public ResponseEntity<String> handleException(InvalidPanelException ex) {
		return new ResponseEntity<String>(ex.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = CustomNullPointerException.class)
	public ResponseEntity<String> handleException1(CustomNullPointerException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = MatchNotFoundException.class)
	public ResponseEntity<String> handleMatch(MatchNotFoundException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = UnauthorizedUserException.class)
	public ResponseEntity<String> handleException123(UnauthorizedUserException ex) {
		return new ResponseEntity<String>(ex.getError(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = InvalidAssociateNameException.class)
    public ResponseEntity<String> handleException(InvalidAssociateNameException ex) {
        return new ResponseEntity<String>(ex.getErrorMessage(), HttpStatus.NOT_FOUND);
    }
	@ExceptionHandler(value = EmptyListException.class)
	public ResponseEntity<String> handleException(EmptyListException ex) {
		return new ResponseEntity<String>(ex.getErrorMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(WrongDateException.class)
	public ResponseEntity<String> wrongdateexceptionhandler(WrongDateException f)
	{
		

		return  new ResponseEntity<String>(f.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	
	
}
