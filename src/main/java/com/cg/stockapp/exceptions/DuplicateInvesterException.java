package com.cg.stockapp.exceptions;

public class DuplicateInvesterException extends RuntimeException{
	public DuplicateInvesterException(String message) {
		super(message);
	}
}
