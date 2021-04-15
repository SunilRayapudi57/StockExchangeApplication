package com.cg.stockapp.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {
	
	@Id
	@GeneratedValue
	private int transactionId;
	
	private LocalDateTime transactionDate;
	private String transactionType;
	
	@ManyToOne
	@JoinColumn(name="stockId")
	private Stock stock;
	
	@ManyToOne
	@JoinColumn(name="inverstorId")
	private Investor investor;
	
	private int quantity;
	
	public Transaction() {
		super();
	}
	public Transaction(LocalDateTime transactionDate, String transactionType, int quantity, Investor investor, Stock stock) {
		super();
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.quantity = quantity;
		this.investor = investor;
		this.stock =stock;
	}
	
	public Transaction(LocalDateTime transactionDate, String transactionType, int quantity) {
		super();
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.quantity = quantity;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public Investor getInvestor() {
		return investor;
	}
	public void setInvestor(Investor investor) {
		this.investor = investor;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
