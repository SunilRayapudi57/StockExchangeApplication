package com.cg.stockapp.exceptions.handlers;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.stockapp.exceptions.CompanyNotFoundException;
import com.cg.stockapp.exceptions.DuplicateCompanyException;

@ControllerAdvice
public class CompanyErrorHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(DuplicateCompanyException.class)
	public ResponseEntity<?> handleDuplicateCompanyException(DuplicateCompanyException dce) {
		Map<String, Object> errorbody = new LinkedHashMap<>();
		errorbody.put("error", "Creation failed");
		errorbody.put("timestamp", LocalDateTime.now());
		errorbody.put("details", dce.getMessage());

		return new ResponseEntity<>(errorbody, HttpStatus.ALREADY_REPORTED);
	}
	
	@ExceptionHandler(CompanyNotFoundException.class)
	public ResponseEntity<?> handleMissingCompanyException(CompanyNotFoundException cne) {
		Map<String, Object> errorbody = new LinkedHashMap<>();
		errorbody.put("error", cne.getOperation()+" failed");
		errorbody.put("timestamp", LocalDateTime.now());
		errorbody.put("details", cne.getMessage());

		return new ResponseEntity<>(errorbody, HttpStatus.NOT_FOUND);
	}
}
