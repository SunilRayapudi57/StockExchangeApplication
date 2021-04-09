package com.cg.stockapp.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="investor")
public class Investor {
	
	@Id
	private int investorId;
	private String investorName;
	private String email;
	private String password;
	private String mobileNo;
	private String gender;
	
	@OneToOne
	@JoinColumn(name="account_no")
	private BankAccount account;
	
	private String status; // approved or notApproved	
	
	@ManyToMany(mappedBy="investors")
	private List<Stock> stocks = new ArrayList<>();
	
	public Investor(int investorId, String investorName, String email, String password, String mobileNo,
			String gender, BankAccount account, String status) {
		super();
		this.investorId = investorId;
		this.investorName = investorName;
		this.email = email; 
		this.password = password; 
		this.mobileNo = mobileNo; 
		this.gender = gender; 
		this.account = account;  
		this.status = status; 
	} 
	public Investor() {}
	
	public List<Stock> getStocks() {
		return stocks;
	}
	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}
	public int getInvestorId() {
		return investorId;
	}
	public void setInvestorId(int investorId) {
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
	@Override
	public String toString() {
		return "InvestorId=" + investorId + ", InvestorName=" + investorName + ", Email=" + email
				+ ", Password=" + password + ", MobileNo=" + mobileNo + ", Gender=" + gender + ", Account=" + account
				+ ", Status=" + status;
	}
	
	
	
}
