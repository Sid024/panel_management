package com.zensar.pm.panel.exception;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	private String messege;
	
	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<String> invalidDataExceptionHandler(InvalidDataException invaildDataException)
	{
		this.messege = invaildDataException.getMessage();

		return  new ResponseEntity<String>(messege,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(WrongDateException.class)
	public ResponseEntity<String> wrongDateExceptionHandler(WrongDateException wrongDataException)
	{
		return  new ResponseEntity<String>(wrongDataException.getMessage(),HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(UnAuthorizedUserException.class)
	public ResponseEntity<String> UnAuthorizedUserException(UnAuthorizedUserException unAuthorizedUserException)
	{
		return  new ResponseEntity<String>(unAuthorizedUserException.getError(),HttpStatus.NOT_FOUND);
	}

	
	


}
