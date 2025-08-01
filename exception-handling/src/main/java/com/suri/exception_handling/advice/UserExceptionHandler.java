package com.suri.exception_handling.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.suri.exception_handling.exception.UserNotFoundException;

@RestControllerAdvice
public class UserExceptionHandler {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,String> handleInvalidArgument(MethodArgumentNotValidException exception){
		
		Map<String,String> errorMap = new HashMap<>();
		
		exception.getBindingResult().getFieldErrors().forEach(error->
		errorMap.put(error.getField(), error.getDefaultMessage()));
		
		return errorMap;
		
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserNotFoundException.class)
	public Map<String,String> handleUserNotFoundException(UserNotFoundException ex){
		
		Map<String,String> unfError = new HashMap<>();
		unfError.put("errorMesssage", ex.getMessage());
		return unfError;
		 
		
	}

}
