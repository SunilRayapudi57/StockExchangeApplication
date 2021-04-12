package com.cg.stockapp.exceptions.handlers;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.stockapp.exceptions.DuplicateStockException;
import com.cg.stockapp.exceptions.StockNotFoundException;

@ControllerAdvice
public class StockErrorHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(DuplicateStockException.class)
	public ResponseEntity<?> handleDuplicateStock(DuplicateStockException dse) {
		Map<String, Object> errorbody = new LinkedHashMap<>();
		errorbody.put("error", "creation failed");
		errorbody.put("timestamp", LocalDateTime.now());
		errorbody.put("details", dse.getMessage());

		return new ResponseEntity<>(errorbody, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(StockNotFoundException.class)
	public ResponseEntity<?> handleMissingStock(StockNotFoundException sne) {
		Map<String, Object> errorbody = new LinkedHashMap<>();
		errorbody.put("error", sne.getOperation()+" failed");
		errorbody.put("timestamp", LocalDateTime.now());
		errorbody.put("details", sne.getMessage());

		return new ResponseEntity<>(errorbody, HttpStatus.CONFLICT);
	}

}
