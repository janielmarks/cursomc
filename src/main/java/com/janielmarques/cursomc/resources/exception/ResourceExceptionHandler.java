package com.janielmarques.cursomc.resources.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.janielmarques.cursomc.resources.StandardError;
import com.janielmarques.cursomc.services.exceptions.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;




	
	@ControllerAdvice
	public class ResourceExceptionHandler {
		
		@ExceptionHandler(ObjectNotFoundException.class)
		public ResponseEntity<StandardError> objecNotFound(ObjectNotFoundException e, HttpServletRequest request) {
			
			
			StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		
	}

}
