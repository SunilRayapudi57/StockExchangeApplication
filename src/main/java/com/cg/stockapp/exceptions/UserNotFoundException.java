package com.cg.stockapp.exceptions;

public class UserNotFoundException extends RuntimeException {
	
	String operation;
	public UserNotFoundException(String operation, String message) {
		super(message);
		this.operation = operation;
	}
	public String getOperation() {
		return this.operation;
	}
	
}
