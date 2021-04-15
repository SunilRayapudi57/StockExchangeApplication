package com.cg.stockapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.stockapp.entities.BankAccount;
import com.cg.stockapp.exceptions.DuplicateBankAccountException;
import com.cg.stockapp.repository.BankAccountRepository;

@Service
public class BankAccountService implements IBankAccountService {

	@Autowired
	BankAccountRepository repo;

	Logger log = LoggerFactory.getLogger(BankAccountService.class);

	@Override
	public boolean addBankAccount(BankAccount account) {
		log.info("addBankAccount() has been invoked");
		long accountNo = account.getAccountNo();
		if (repo.existsById(accountNo)) {
			log.warn("DuplicateBankAccount : Account already exists with account no "+accountNo);
			throw new DuplicateBankAccountException("Account already exists with account no " + accountNo);
		} else {
			repo.save(account);
			log.info("A bank account with account no "+accountNo+" has beeb added");
			return true;
		}
	}

}
