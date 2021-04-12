package com.cg.stockapp.exceptions.handlers;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.stockapp.exceptions.DuplicateUserException;
import com.cg.stockapp.exceptions.UserNotFoundException;

@ControllerAdvice
public class UserErrorHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(DuplicateUserException.class)
	public ResponseEntity<?> handleDuplicateUser(DuplicateUserException due) {
		Map<String, Object> errorbody = new LinkedHashMap<>();
		errorbody.put("error", "creation failed");
		errorbody.put("timestamp", LocalDateTime.now());
		errorbody.put("details", due.getMessage());

		return new ResponseEntity<>(errorbody, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> handleMissingUser(UserNotFoundException une) {
		Map<String, Object> errorbody = new LinkedHashMap<>();
		errorbody.put("error", une.getOperation()+" failed");
		errorbody.put("timestamp", LocalDateTime.now());
		errorbody.put("details", une.getMessage());

		return new ResponseEntity<>(errorbody, HttpStatus.NOT_FOUND);
	}
	
}
