package com.cg.stockapp.exceptions.handlers;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.stockapp.exceptions.DuplicateCompanyException;
import com.cg.stockapp.exceptions.DuplicateManagerException;
import com.cg.stockapp.exceptions.DuplicateStockException;
import com.cg.stockapp.exceptions.DuplicateUserException;
import com.cg.stockapp.exceptions.EmptyTableException;

@ControllerAdvice
public class ApplicationErrorHandler extends ResponseEntityExceptionHandler {


	@ExceptionHandler(DuplicateStockException.class)
	public ResponseEntity<?> handleDuplicateManager(DuplicateStockException dse) {
		Map<String, Object> errorbody = new LinkedHashMap<>();
		errorbody.put("error", "creation failed");
		errorbody.put("timestamp", LocalDateTime.now());
		errorbody.put("details", dse.getMessage());

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
	
	@ExceptionHandler(EmptyTableException.class)
	public ResponseEntity<?> handleEmptyDataException(EmptyTableException dte) {
		Map<String, Object> errorbody = new LinkedHashMap<>();
		errorbody.put("error", "Table is Empty");
		errorbody.put("timestamp", LocalDateTime.now());
		errorbody.put("details", dte.getMessage());

		return new ResponseEntity<>(errorbody, HttpStatus.NOT_FOUND);
	}
	
}
