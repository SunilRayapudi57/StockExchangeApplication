package com.cg.stockapp.dao;

import java.util.List;

import com.cg.stockapp.dto.Company;
import com.cg.stockapp.dto.Investor;
import com.cg.stockapp.dto.Stock;

public interface InvestorDao {
	
//	public boolean addInvestor(Investor inv);
	public List<Investor> getAllInvestor();
//	public Investor getInvestorDetails(String investorId);
//	public Investor updateInvestor(Investor info);
//	public Investor deleteInvestor(Investor inv);
//	public List<Investor> viewAlInvestor(Stock stock);
//	public List<Investor> viewAllInvestor(Company company);
//	public Investor buyStock(Stock stock, Investor investor, int quantity);
//	public Investor sellStock(Stock stock, Investor investor, int quantity);

}
