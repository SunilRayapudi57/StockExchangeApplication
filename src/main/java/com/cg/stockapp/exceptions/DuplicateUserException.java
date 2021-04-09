package com.cg.stockapp.exceptions;

public class DuplicateUserException extends RuntimeException {
	
	public DuplicateUserException(String message) {
		super(message);
	}
	
}
