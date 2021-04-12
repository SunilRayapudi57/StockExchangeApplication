package com.cg.stockapp.exceptions;

public class StockNotFoundException extends RuntimeException {
	
	private String operation;
	public StockNotFoundException(String operation, String message) {
		super(message);
		this.operation = operation;
	}
	
	public String getOperation() {
		return this.operation;
	}
	
}
 