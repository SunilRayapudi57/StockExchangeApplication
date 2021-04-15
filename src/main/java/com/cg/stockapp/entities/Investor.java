package com.cg.stockapp.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="investor")
public class Investor {
	
	@Id
	@Column(name = "INVESTOR_ID")
	private String investorId;
	@Column(name = "INVESTOR_NAME")
	private String investorName;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "MOBILE_NO")
	private String mobileNo;
	@Column(name = "GENDER")
	private String gender;
	@Column(name = "STATUS")
	private String status; // approved or pending	       
	
	@JsonIgnore
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="accountNo")
	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
	private BankAccount account;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "investors", fetch = FetchType.LAZY)
	private List<Stock> stocks = new ArrayList<>();
	
	@JsonIgnore
	@Transient
	private Map<Stock, Integer> stockQuantity = new HashMap<>();
	
	
	public Investor(String investorId, String investorName, String email, String password, String mobileNo,
			String gender, String status) {
		super();
		this.investorId = investorId;
		this.investorName = investorName;
		this.email = email;
		this.password = password;
		this.mobileNo = mobileNo;
		this.gender = gender;
		this.status = status;
	}

	public Investor() {
		super();
	}
	
	public void addStock(Stock stock, int quantity) {
		this.stocks.add(stock);
		this.stockQuantity.put(stock, quantity);
	}
	public void removeStock(Stock stock) {
		this.stocks.remove(stock);
	}
	public void updateStock(Stock stock, int quantity) {
		this.stockQuantity.put(stock, quantity);
	}
	
	public Map<Stock, Integer> getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(Map<Stock, Integer> stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public String getInvestorId() {
		return investorId;
	}
	public void setInvestorId(String investorId) {
		this.investorId = investorId;
	}
	public String getInvestorName() {
		return investorName;
	}
	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public BankAccount getAccount() {
		return account;
	}
	public void setAccount(BankAccount account) {
		this.account = account;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public List<Stock> getStocks() {
		return stocks;
	}
	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}
	
}
