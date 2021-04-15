package com.cg.stockapp.service;

import com.cg.stockapp.entities.Transaction;

public interface ITransactionService {
	
	public boolean addTransaction(Transaction transaction);
	
	public Transaction getTransactionDetails(int transactionId);

}
