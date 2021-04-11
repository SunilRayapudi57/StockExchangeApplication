package com.cg.stockapp.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="stock")
public class Stock  {
	
	@Id
    private int stockId;
	private String companyId;
	private String stockName;
	private int quantity;
	private String type;
	private double avgPrice;
	private int stockTotal;
	private double profitLoss;
	private String status; // active or non-Active
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "Stocks_Investors",
			joinColumns = @JoinColumn(name = "stock_Id"),
			inverseJoinColumns = @JoinColumn(name = "investor_id")
		)
	private Set<Investor> investors = new HashSet<>();
	
	public Stock() {
		super();
	}
	
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
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
	public int getStockTotal() {
		return stockTotal;
	}
	public void setStockTotal(int stockTotal) {
		this.stockTotal = stockTotal;
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
	public Set<Investor> getInvestors() {
		return investors;
	}
	public void setInvestors(Set<Investor> investors) {
		this.investors = investors;
	}
	
}