package com.cg.stockapp.exceptions;

public class ManagerNotFoundException extends RuntimeException {
	
	private String operation;
	public ManagerNotFoundException(String operation, String message) {
		super(message);
		this.operation = operation;
	}
	
	public String getOperation() {
		return this.operation;
	}
	
}
