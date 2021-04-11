package com.cg.stockapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.stockapp.entities.BankAccount;
import com.cg.stockapp.repository.BankAccountRepository;

@Service
public class BankAccountService implements IBankAccountService{

	@Autowired
	BankAccountRepository repo;
	
	@Override
	public boolean addBankAccount(BankAccount acc) {
		if(repo.existsById(acc.getAccountNo()))
			return false;
		repo.save(acc);
		return true;
	}

}
