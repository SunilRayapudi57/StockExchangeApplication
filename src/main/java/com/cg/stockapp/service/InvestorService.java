package com.cg.stockapp.service;

import java.util.List;

import com.cg.stockapp.entities.BankAccount;
import com.cg.stockapp.entities.Company;
import com.cg.stockapp.entities.Investor;
import com.cg.stockapp.entities.Stock;

public interface InvestorService {
	
	public List<Investor> getAllInvestor();
	
	public boolean addInvestor(Investor inv);
	
	public boolean deleteInvestor(String investorId);
	
	public Investor getInvestorDetails(String investorId);
	
	public boolean updateInvestor(Investor info);

	public boolean addBankDetails(String investorId, BankAccount account);
	
	public BankAccount getBankDetails(String investorId);
	
	public List<Investor> viewAllInvestorByStock(String stockId);
	
//	public List<Investor> viewAllInvestorByCompany(String companyId);

	public boolean buyStock(String investorId, String stockId, int quantity);

	boolean sellStock(String investorId, String stockId, int quantity);
	
}
