package com.cg.stockapp.exceptions.handlers;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.stockapp.exceptions.AdminNotFoundException;
import com.cg.stockapp.exceptions.DuplicateAdminException;

@ControllerAdvice
public class AdminErrorHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(DuplicateAdminException.class)
	public ResponseEntity<?> handleDuplicateAdmin(DuplicateAdminException dae) {
		Map<String, Object> errorbody = new LinkedHashMap<>();
		errorbody.put("error", "Creation failed");
		errorbody.put("timestamp", LocalDateTime.now());
		errorbody.put("details", dae.getMessage());

		return new ResponseEntity<>(errorbody, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(AdminNotFoundException.class)
	public ResponseEntity<?> handleMissingAdmin(AdminNotFoundException ane) {
		Map<String, Object> errorbody = new LinkedHashMap<>();
		errorbody.put("error", ane.getOperation()+" failed");
		errorbody.put("timestamp", LocalDateTime.now());
		errorbody.put("details", ane.getMessage());

		return new ResponseEntity<>(errorbody, HttpStatus.NOT_FOUND);
	}
	
}
