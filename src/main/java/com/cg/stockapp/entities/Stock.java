package com.cg.stockapp.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="stock")
public class Stock  {
	
	@Id
	@Column(name = "STOCK_ID")
    private String stockId;
	
	@Column(name = "STOCK_NAME")
	private String stockName;
	
	@Column(name = "QUANTITY")
	private int quantity;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "AVG_PRICE")
	private double avgPrice;
	
	@Column(name = "STOCK_TOTAL")
	private int stockTotal;
	
	@Column(name = "PROFIT_LOSS")
	private double profitLoss;
	
	@Column(name = "STATUS")
	private String status; // active or non-Active
	
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
	private Company company;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "Stocks_Investors",
			joinColumns = @JoinColumn(name = "stock_Id"),
			inverseJoinColumns = @JoinColumn(name = "investor_id")
		)
	private List<Investor> investors = new ArrayList<>();
	
	
	public Stock(String stockId, String stockName, int quantity, String type, double avgPrice, int stockTotal,
			double profitLoss, String status) {
		super();
		this.stockId = stockId;
		this.stockName = stockName;
		this.quantity = quantity;
		this.type = type;
		this.avgPrice = avgPrice;
		this.stockTotal = stockTotal;
		this.profitLoss = profitLoss;
		this.status = status;
	}

	public Stock() {
		super();
	}
	
	public void addInvestor(Investor investor) {
		this.investors.add(investor);
	}
	public void removeInvestor(Investor investor) {
		this.investors.remove(investor);
	}
	
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
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

	public List<Investor> getInvestors() {
		return investors;
	}
	public void setInvestors(List<Investor> investors) {
		this.investors = investors;
	}
	
	
}