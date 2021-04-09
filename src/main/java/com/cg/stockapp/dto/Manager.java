package com.cg.stockapp.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="manager")
public class Manager {
	@Id
	private int managerId;
	private String managerName;
	private String email;
	private String password;
	private String mobileNum;
	@OneToOne
	@JoinColumn(name="company_id")
	private Company company ;
	
	public Manager(int managerId, String managerName, String email, String password, String mobileNum, Company company) {
		super();
		this.managerId = managerId;
		this.managerName = managerName;
		this.email = email;
		this.password = password;
		this.mobileNum = mobileNum;
		this.company = company;
	}
	public Manager() {
		super();
	}
	
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmailId(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Override
	public String toString() {
		return "Manager [managerId=" + managerId + ", managerName=" + managerName + ", emailId=" + email
				+ ", mobileNum=" + mobileNum + ", company=" + company + "]";
	}
	
}
