package com.cg.stockapp.exceptions.handlers;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.stockapp.exceptions.BankAccountNotFoundException;
import com.cg.stockapp.exceptions.DuplicateBankAccountException;
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

		return new ResponseEntity<>(errorbody, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvestorNotFoundException.class)
	public ResponseEntity<?> handleMissingInvestor(InvestorNotFoundException ine) {
		Map<String, Object> errorbody = new LinkedHashMap<>();
		errorbody.put("error", ine.getOperation()+" failed");
		errorbody.put("timestamp", LocalDateTime.now());
		errorbody.put("details", ine.getMessage());

		return new ResponseEntity<>(errorbody, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DuplicateBankAccountException.class)
	public ResponseEntity<?> handleDuplicateBankAccount(DuplicateBankAccountException dbe) {
		Map<String, Object> errorbody = new LinkedHashMap<>();
		errorbody.put("error", "Creation failed");
		errorbody.put("timestamp", LocalDateTime.now());
		errorbody.put("details", dbe.getMessage());

		return new ResponseEntity<>(errorbody, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BankAccountNotFoundException.class)
	public ResponseEntity<?> handleMissingInvestor(BankAccountNotFoundException bne) {
		Map<String, Object> errorbody = new LinkedHashMap<>();
		errorbody.put("error", bne.getOperation()+" failed");
		errorbody.put("timestamp", LocalDateTime.now());
		errorbody.put("details", bne.getMessage());

		return new ResponseEntity<>(errorbody, HttpStatus.NOT_FOUND);
	}
	
}
