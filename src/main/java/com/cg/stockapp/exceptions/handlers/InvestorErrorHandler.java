package com.cg.stockapp.exceptions.handlers;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.stockapp.exceptions.DuplicateInvesterException;
import com.cg.stockapp.exceptions.InvestorNotFoundException;

@ControllerAdvice
public class InvestorErrorHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(DuplicateInvesterException.class)
	public ResponseEntity<?> handleDuplicateInvestor(DuplicateInvesterException die) {
		Map<String, Object> errorbody = new LinkedHashMap<>();
		errorbody.put("error", "creation failed");
		errorbody.put("timestamp", LocalDateTime.now());
		errorbody.put("details", die.getMessage());

		return new ResponseEntity<>(errorbody, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(InvestorNotFoundException.class)
	public ResponseEntity<?> handleMissingInvestor(InvestorNotFoundException ine) {
		Map<String, Object> errorbody = new LinkedHashMap<>();
		errorbody.put("error", "deletion failed");
		errorbody.put("timestamp", LocalDateTime.now());
		errorbody.put("details", ine.getMessage());

		return new ResponseEntity<>(errorbody, HttpStatus.NOT_FOUND);
	}
	
}
