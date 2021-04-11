package com.cg.stockapp.exceptions;

public class DuplicateStockException extends RuntimeException {
	
	public DuplicateStockException(String message) {
		super(message);
	}
	
}
