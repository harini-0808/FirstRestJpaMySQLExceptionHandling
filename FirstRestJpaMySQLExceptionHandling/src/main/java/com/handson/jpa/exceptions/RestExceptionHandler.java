package com.handson.jpa.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler({NullPointerException.class})
	//redundant when overridden
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	protected ResponseEntity<Object> errorHandler(NullPointerException e, WebRequest req){
		ErrorResponse response = new ErrorResponse();
		response.setErrorCode("406");
		response.setMessage(e.getMessage());
		response.setTime(new Date());
		
		return handleExceptionInternal(e,response,new HttpHeaders(),HttpStatus.NOT_ACCEPTABLE,req);
	}
	
	@ExceptionHandler({NotFoundException.class})
	protected ResponseEntity<Object> errorHandler(NotFoundException e, WebRequest req){
		ErrorResponse response = new ErrorResponse();
		response.setErrorCode("404");
		response.setMessage(e.getMessage());
		response.setTime(new Date());
		
		return handleExceptionInternal(e,response,new HttpHeaders(),HttpStatus.NOT_FOUND,req);
	}

	//general exception handler
	@ExceptionHandler({Exception.class})
	protected ResponseEntity<Object> errorHandler(Exception e, WebRequest req){
		ErrorResponse response = new ErrorResponse();
		response.setErrorCode("404");
		response.setMessage(e.getMessage());
		response.setTime(new Date());
		
		return handleExceptionInternal(e,response,new HttpHeaders(),HttpStatus.NOT_FOUND,req);
	}
	
}
