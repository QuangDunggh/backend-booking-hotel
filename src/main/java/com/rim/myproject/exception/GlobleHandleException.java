package com.rim.myproject.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobleHandleException {

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String> handleNotFoundResourceException(NotFoundResourceException ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("errorMessage", ex.getMessage());
		return errors;
	}
	
	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String,String> handleCanNotConvertImageException(CanNotConvertImageException ex) {
		Map<String,String> errors = new HashMap<> ();
		errors.put("errorMessage", ex.getMessage());
		return errors;
	}
}


