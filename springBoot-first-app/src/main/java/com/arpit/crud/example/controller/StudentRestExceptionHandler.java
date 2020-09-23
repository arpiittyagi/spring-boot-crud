package com.arpit.crud.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {
	
	//exception handeler mathod
		@ExceptionHandler
		public ResponseEntity<ErrorMessageResponse> handleException(ProductNotFoundException pr){
			ErrorMessageResponse error = new ErrorMessageResponse();
			error.setStatus(HttpStatus.NOT_FOUND.value());
			error.setMessage(pr.getMessage());
			error.setTimeStamp(System.currentTimeMillis());
			return new  ResponseEntity<>(error,HttpStatus.NOT_FOUND);
		}
		
		
		//add generic eexception handeler
		
		@ExceptionHandler
		public ResponseEntity<ErrorMessageResponse> genericException(Exception ex){
			ErrorMessageResponse error = new ErrorMessageResponse();
			error.setStatus(HttpStatus.BAD_REQUEST.value());
			error.setMessage(ex.getMessage());
			error.setTimeStamp(System.currentTimeMillis());
			return new  ResponseEntity<>(error,HttpStatus.NOT_FOUND);
			
		}

}
