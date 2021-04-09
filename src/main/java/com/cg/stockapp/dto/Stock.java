package com.cg.stockapp.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="stock")
public class Stock  {
	
	@Id
    private int stockId;
	private String stockName;
	
	@ManyToMany
	@JoinColumn(name="investorId")
	private List<Investor> investors;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="company_Id")
	private Company companyId;
	
	private int quantity;
	private String type;
	private double avgPrice;
	private double profitLoss;
	private String status; // active or notActive
	
	
//	public Stock(int stockId, String stockName, List<Investor> investors, Company companyId, int quantity, String type,
//			double avgPrice, double profitLoss, String status) {
//		super();
//		this.stockId = stockId;
//		this.stockName = stockName;
//		this.investors = investors;
//		this.companyId = companyId;
//		this.quantity = quantity;
//		this.type = type;
//		this.avgPrice = avgPrice;
//		this.profitLoss = profitLoss;
//		this.status = status;
//	}
	public Stock(int stockId, String stockName, Investor investor, Company companyId, int quantity, String type,
			double avgPrice, double profitLoss, String status) {
		super();
		this.stockId = stockId;
		this.stockName = stockName;
		this.investors.add(investor);
		this.companyId = companyId;
		this.quantity = quantity;
		this.type = type;
		this.avgPrice = avgPrice;
		this.profitLoss = profitLoss;
		this.status = status;
	}
	public Stock() {
		super();
	}
	
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public List<Investor> getInvestorId() {
		return investors;
	}
	public void setInvestorId(List<Investor> investors) {
		this.investors = investors;
	}
	public Company getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Company companyId) {
		this.companyId = companyId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(double avgPrice) {
		this.avgPrice = avgPrice;
	}
	public double getProfitLoss() {
		return profitLoss;
	}
	public void setProfitLoss(double profitLoss) {
		this.profitLoss = profitLoss;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

	@Override
	public String toString() {
		return "Stock [stockId=" + stockId + ", stockName=" + stockName + ", Investors=" + investors + ", companyId="
				+ companyId + ", quantity=" + quantity + ", type=" + type + ", avgPrice=" 
				+ avgPrice + ", profitLoss=" + profitLoss + ", status=" + status + "]";
	}
	
}