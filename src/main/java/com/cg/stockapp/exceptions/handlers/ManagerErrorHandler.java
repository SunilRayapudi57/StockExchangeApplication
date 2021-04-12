package com.cg.stockapp.exceptions.handlers;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.stockapp.exceptions.DuplicateManagerException;
import com.cg.stockapp.exceptions.ManagerNotFoundException;

public class ManagerErrorHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(DuplicateManagerException.class)
	public ResponseEntity<?> handleDuplicateManager(DuplicateManagerException dme) {
		Map<String, Object> errorbody = new LinkedHashMap<>();
		errorbody.put("error", "creation failed");
		errorbody.put("timestamp", LocalDateTime.now());
		errorbody.put("details", dme.getMessage());

		return new ResponseEntity<>(errorbody, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(ManagerNotFoundException.class)
	public ResponseEntity<?> handleMissingManager(ManagerNotFoundException mne) {
		Map<String, Object> errorbody = new LinkedHashMap<>();
		errorbody.put("error", mne.getOperation()+" failed");
		errorbody.put("timestamp", LocalDateTime.now());
		errorbody.put("details", mne.getMessage());

		return new ResponseEntity<>(errorbody, HttpStatus.CONFLICT);
	}
	
}
