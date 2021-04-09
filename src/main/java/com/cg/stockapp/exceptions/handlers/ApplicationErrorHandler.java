package com.cg.stockapp.exceptions.handlers;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.stockapp.exceptions.DuplicateAdminException;
import com.cg.stockapp.exceptions.DuplicateCompanyException;
import com.cg.stockapp.exceptions.DuplicateInvesterException;
import com.cg.stockapp.exceptions.DuplicateManagerException;
import com.cg.stockapp.exceptions.DuplicateUserException;

@ControllerAdvice
public class ApplicationErrorHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DuplicateInvesterException.class)
	public ResponseEntity<?> handleDuplicateInvestor(DuplicateInvesterException die) {
		Map<String, Object> errorbody = new LinkedHashMap<>();
		errorbody.put("error", "creation failed");
		errorbody.put("timestamp", LocalDateTime.now());
		errorbody.put("details", die.getMessage());

		return new ResponseEntity<>(errorbody, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(DuplicateAdminException.class)
	public ResponseEntity<?> handleDuplicateAdmin(DuplicateAdminException dae) {
		Map<String, Object> errorbody = new LinkedHashMap<>();
		errorbody.put("error", "creation failed");
		errorbody.put("timestamp", LocalDateTime.now());
		errorbody.put("details", dae.getMessage());

		return new ResponseEntity<>(errorbody, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(DuplicateManagerException.class)
	public ResponseEntity<?> handleDuplicateManager(DuplicateManagerException dme) {
		Map<String, Object> errorbody = new LinkedHashMap<>();
		errorbody.put("error", "creation failed");
		errorbody.put("timestamp", LocalDateTime.now());
		errorbody.put("details", dme.getMessage());

		return new ResponseEntity<>(errorbody, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(DuplicateUserException.class)
	public ResponseEntity<?> handleDuplicateUser(DuplicateUserException due) {
		Map<String, Object> errorbody = new LinkedHashMap<>();
		errorbody.put("error", "creation failed");
		errorbody.put("timestamp", LocalDateTime.now());
		errorbody.put("details", due.getMessage());

		return new ResponseEntity<>(errorbody, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(DuplicateCompanyException.class)
	public ResponseEntity<?> handleDuplicateCompany(DuplicateCompanyException dce) {
		Map<String, Object> errorbody = new LinkedHashMap<>();
		errorbody.put("error", "creation failed");
		errorbody.put("timestamp", LocalDateTime.now());
		errorbody.put("details", dce.getMessage());

		return new ResponseEntity<>(errorbody, HttpStatus.CONFLICT);
	}
	
}
