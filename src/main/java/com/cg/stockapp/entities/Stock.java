package com.cg.stockapp.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@Table(name="stock")
public class Stock  {
	
	@Id
	@Column(name = "STOCK_ID", nullable = false)
    private String stockId;
	
	@NotNull
	@Column(name = "STOCK_NAME")
	private String stockName;
	
	@NotNull
	@Column(name = "QUANTITY")
	private int quantity;
	
	@NotNull
	@Pattern(regexp = "(NSE|BSE)")
	@Column(name = "TYPE")
	private String type;
	
	@NotNull
	@Column(name = "STOCK_PRICE")
	private double stockPrice;
	
	@Transient
	@Column(name = "STOCK_TOTAL")
	private double stockTotal;
	
	@Column(name = "PROFIT_LOSS")
	private double profitLoss;
	
	@JsonIgnore
	@Column(name = "STATUS")
	private String status = "active"; // active or non-Active
	
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
	private Company company;
	
	@JsonIgnore
	@OneToMany(targetEntity = Transaction.class, mappedBy = "stock")
	private List<Transaction> transactions = new ArrayList<>();
	
	
	public Stock(String stockId, String stockName, int quantity, String type, double stockPrice, Company company) {
		super();
		this.stockId = stockId;
		this.stockName = stockName;
		this.quantity = quantity;
		this.type = type;
		this.stockPrice = stockPrice;
		this.company = company;
		this.stockTotal = this.quantity*this.stockPrice;
		
	}
	public Stock() {
		super();
	}
	
	public void addTransaction(Transaction transaction) {
		this.transactions.add(transaction);
	}
	
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
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
	public double getStockTotal() {
		return stockTotal;
	}
	public void setStockTotal(double stockTotal) {
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
	
	
}