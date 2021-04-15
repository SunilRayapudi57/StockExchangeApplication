package com.cg.stockapp.exceptions;

public class BankAccountNotFoundException extends RuntimeException {
	
	private String operation;
	public BankAccountNotFoundException(String operation, String message) {
		super(message);
		this.operation = operation;
	}
	
	public String getOperation() {
		return this.operation;
	}

}
