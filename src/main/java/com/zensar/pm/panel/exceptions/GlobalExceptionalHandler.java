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
	
	@ExceptionHandler(value = DuplicateStatusException.class)
	public ResponseEntity<String> duplicateStatus(DuplicateStatusException ex) {
		return new ResponseEntity<String>("List Empty", HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = AssociateIdAlreadyExistsException.class)
	public ResponseEntity<String> handleException(AssociateIdAlreadyExistsException ex) {
		return new ResponseEntity<String>("Associate Id Already Exists", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = EmailAlreadyExistException.class)
	public ResponseEntity<String> handleException(EmailAlreadyExistException ex) {
		return new ResponseEntity<String>("Email Already Exists", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = GradeNotFoundException.class)
	public ResponseEntity<String> handleException(GradeNotFoundException ex) {
		return new ResponseEntity<String>("Grade not found", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = InterviewTypeNotFoundException.class)
	public ResponseEntity<String> handleException(InterviewTypeNotFoundException ex) {
		return new ResponseEntity<String>("Interview Type not found", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = NoSuchRoleFoundException.class)
	public ResponseEntity<String> handleException(NoSuchRoleFoundException ex) {
		return new ResponseEntity<String>("No Such Role found", HttpStatus.BAD_REQUEST);
	}
    
	@ExceptionHandler(value = PanelAlreadyExists.class)
	public ResponseEntity<String> handleException(PanelAlreadyExists ex) {
		return new ResponseEntity<String>("Panel Already Exists", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = PanelCandidateRoleNotFoundException.class)
	public ResponseEntity<String> handleException(PanelCandidateRoleNotFoundException ex) {
		return new ResponseEntity<String>("Panel Candidate Role Not Found", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler (value = UserNotFoundException.class)
	public ResponseEntity<String> handleException(UserNotFoundException ex){
		return new ResponseEntity<String>("User Not Found", HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler (value = PanelNotFound.class)
    public ResponseEntity<String> handleException(PanelNotFound ex){
        return new ResponseEntity<String>("Panel Not Found", HttpStatus.BAD_REQUEST);

    }
	
	@ExceptionHandler(MailNotSentException.class)
    public ResponseEntity<String> exception(MailNotSentException exception) {
        //ExceptionEntity message = new ExceptionEntity(exception.getMessage());
        return new ResponseEntity<>("Something went wrong while sending the mail", HttpStatus.BAD_GATEWAY);
    }
	@ExceptionHandler(TimeFormatException.class)
    ResponseEntity<String> handleTimeFormat(TimeFormatException ex)
    {
	 return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(DateRangeException.class)
    ResponseEntity<String> handleDateRange(DateRangeException ex)
	{
	return new ResponseEntity<String>("ToDate should be in that week",HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DateAlreadyExistsException.class)
	ResponseEntity<String> handleDateAlreadyExists(DateAlreadyExistsException ex)
	{
	return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	
}
