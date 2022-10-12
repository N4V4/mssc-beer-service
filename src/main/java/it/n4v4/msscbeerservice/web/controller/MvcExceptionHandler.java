package it.n4v4.msscbeerservice.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MvcExceptionHandler {
	

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List> constraintViolationHandler(ConstraintViolationException e){
		
		List<String> errorsList = new ArrayList<>(e.getConstraintViolations().size());
		
		e.getConstraintViolations().forEach(constraintViolation -> errorsList.add(constraintViolation.toString()));
		
		return new ResponseEntity<>(errorsList, HttpStatus.BAD_REQUEST);
	}

}
