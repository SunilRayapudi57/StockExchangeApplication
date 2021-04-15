package com.cg.stockapp.exceptions;

public class InsufficientSharesException extends RuntimeException {

	public InsufficientSharesException(String message) {
		super(message);
	}
	
}
