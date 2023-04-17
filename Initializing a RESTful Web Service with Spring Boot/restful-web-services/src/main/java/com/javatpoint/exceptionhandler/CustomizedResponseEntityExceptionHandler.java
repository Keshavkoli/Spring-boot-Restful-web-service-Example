package com.javatpoint.exceptionhandler;

import java.util.Date;

import org.apache.activemq.command.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.javatpoint.exception.response.ExceptionResponseConnect;

@ControllerAdvice
@RestController
// with this class we can handle the (500Internal Server Error) and provide a proper message
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(Exception.class)
	// override method of ResponseEntityExceptionHandler class
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		// creating exception response structure
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		// returning exception structure and specific status
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// with the below method we can change the status value inside the postman
	@ExceptionHandler(UserNotFoundException.class)
	// override method of ResponseEntityExceptionHandler class
	public final ResponseEntity<Object> handleUserNotFoundExceptions(UserNotFoundException ex, WebRequest request) {
		// creating exception response structure
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		// returning exception structure and Not Found status
		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	// the below method is used to handle the bad request
	// for example when we put some validations on fields that time the Customers
	// should know the validation
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponseConnect exceptionResponse = new ExceptionResponseConnect(new Date(), ex.getMessage(),
				ex.getBindingResult().toString());
		// returning exception structure and specific status
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
