package com.cg.stockapp.exceptions;

public class CompanyNotFoundException extends RuntimeException {
	
	private String operation;
	public CompanyNotFoundException(String operation, String message) {
		super(message);
		this.operation = operation;
	}
	
	public String getOperstion() {
		return this.operation;
	}
}
