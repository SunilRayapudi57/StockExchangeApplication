package com.cg.stockapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "manager")
public class Manager {
	
	@Id
	@Column(name = "MANAGER_ID", nullable = false)
	private String managerId;
	
	@Column(name = "MANAGER_NAME")
	private String managerName;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "MOBILE_NUM" )
	private String mobileNum;

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId")
	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
	private Company company;
	
	public Manager(String managerId, String managerName, String email, String password, String mobileNum) {
		super();
		this.managerId = managerId;
		this.managerName = managerName;
		this.email = email;
		this.password = password;
		this.mobileNum = mobileNum;
	}

	public Manager() {
		super();
	}
	
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
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
	public void setEmail(String email) {
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
	
	
}
