package com.cg.stockapp.exceptions.handlers;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.stockapp.exceptions.EmptyTableException;

@ControllerAdvice
public class ApplicationErrorHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errorList = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList()); 

		Map<String, Object> errorBody = new LinkedHashMap<>();
		errorBody.put("error", errorList);
		errorBody.put("Timestamp", LocalDateTime.now());
		errorBody.put("details", "Data recieved is not valid");
		return new ResponseEntity<Object>(errorBody, HttpStatus.NOT_ACCEPTABLE);
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
