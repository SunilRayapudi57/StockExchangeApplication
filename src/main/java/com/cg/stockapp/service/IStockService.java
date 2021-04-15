package com.cg.stockapp.service;

import java.util.List;

import com.cg.stockapp.entities.Investor;
import com.cg.stockapp.entities.Stock;

public interface IStockService {
	
	public boolean addStock(Stock stock);
	public List<Stock> viewAllStock();
	public boolean removeStockDetails(String stockId);
	public boolean updateStockDetails(Stock stock);
	public Stock viewStockDetails(String stockId);
	public List<Stock> viewStockByCompany(String companyName);
	
//	public List<Stock> viewStockByInvestor(Investor investor);
//	public List<Stock> viewAllGrowingStock();
//	public List<Stock> viewAllReducingStock();
	
}
