package com.cg.stockapp.exceptions;

public class DuplicateBankAccountException extends RuntimeException {
	
	public DuplicateBankAccountException(String message) {
		super(message);
	}
	
}
