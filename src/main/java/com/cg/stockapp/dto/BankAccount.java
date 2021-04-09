package com.cg.stockapp.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="bankaccount")
public class BankAccount {
	
	@Id
	private long accountNo;
	private String bankName;
	private String branchName;
	private String ifscCode;
	@OneToOne(mappedBy="account")
	private Investor investor;
	public BankAccount(long accountNo, String bankName, String branchName, String ifscCode) {
		super();
		this.accountNo = accountNo;
		this.bankName = bankName;
		this.branchName = branchName;
		this.ifscCode = ifscCode;
	}
	public BankAccount() {}
	
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
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public Investor getInvestor() {
		return investor;
	}
	public void setInvestor(Investor investor) {
		this.investor = investor;
	}
	@Override
	public String toString() {
		return "AccountNo=" + accountNo + ", BankName=" + bankName + ", BranchName=" + branchName
				+ ", IFSC="+ifscCode;
	}
	
}
