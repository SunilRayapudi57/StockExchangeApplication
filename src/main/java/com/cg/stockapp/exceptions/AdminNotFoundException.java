package com.cg.stockapp.exceptions;

public class AdminNotFoundException extends RuntimeException {
	
	private String operation;
	public AdminNotFoundException(String operation, String message) {
		super(message);
		this.operation = operation;
	}
	
	public String getOperation() {
		return this.operation;
	}

}
