package com.qsp.green_corner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice

public class GlobalExceptionHandler {
	
	public ResponseEntity<String> handleUserNotFound(LoginFailed ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<String> handleNoPlantFoundInPlant(NoPlantFoundInCart np){
		return new ResponseEntity<String>(np.getMessage(),HttpStatus.NO_CONTENT);
	}

}
