package com.cg.stockapp.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@Table(name="investor")
public class Investor {
	
	@Id
	@Column(name = "INVESTOR_ID")
	private String investorId;
	
	@NotNull
	@Length(min = 5, max = 20, message = "Username should be from 5 to 20 characters")
	@Column(name = "INVESTOR_NAME")
	private String investorName;
	
	@Pattern(regexp = "^[A-Za-z0-9._]+@[A-za-z0-9]+[.][A-za-z]{2,5}$", message = "Email is not valid")
	@Column(name = "EMAIL")
	private String email;
	
	@NotNull
	@Pattern(regexp = "[A-Za-z]+[0-9]+[!@#$%^&*]+", message = "Password should atleast contain a digit and a special character")
	@Length(min = 7, max = 20, message = "Username should be from 7 to 20 characters")
	@Column(name = "PASSWORD")
	private String password;
	
	@NotNull
	@Pattern(regexp = "[0-9]{10}", message = "Invalid Mobile number")
	@Column(name = "MOBILE_NO")
	private String mobileNo;
	
	@Pattern(regexp = "(male|female)", message = "Invalid Gender")
	@Column(name = "GENDER")
	private String gender;
	
	@JsonIgnore
	@Column(name = "STATUS")
	private String status = "pending"; // approved or pending
	
	@JsonIgnore
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="accountNo")
	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
	private BankAccount account;
	
	@JsonIgnore
	@OneToMany(targetEntity = Transaction.class, mappedBy = "investor")
	private List<Transaction> transactions = new ArrayList<>();
	
	public Investor(String investorId, String investorName, String email, String password, String mobileNo,
			String gender) {
		super();
		this.investorId = investorId;
		this.investorName = investorName;
		this.email = email;
		this.password = password;
		this.mobileNo = mobileNo;
		this.gender = gender;
	}

	public Investor() {
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

	
}
