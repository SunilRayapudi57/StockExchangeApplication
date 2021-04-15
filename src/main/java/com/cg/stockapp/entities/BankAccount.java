package com.cg.stockapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
@Table(name="bankaccount")
public class BankAccount {
	
	@Id
	@Column(name = "ACCOUNT_NO", nullable = false, updatable = false)
	private long accountNo;
	
	@NotNull
	@Column(name = "BANK_NAME")
	private String bankName;
	
	@NotNull
	@Column(name = "BRANCH_NAME" )
	private String branchName;
	
	@NotNull
	@Pattern(regexp = "^[A-Z]+[0-9]+")
	@Column(name = "IFSC_CODE")
	private String ifscCode;
	
	@JsonIgnore
	@OneToOne(mappedBy = "account")
	private Investor investor;
	
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	
}
