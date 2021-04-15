package com.cg.stockapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;



@Entity
@Table(name = "manager")
public class Manager {
	
	@Id
	@Column(name = "MANAGER_ID", nullable = false)
	private String managerId;
	
	@NotNull
	@Length(min = 5, max = 20, message = "Username should be from 5 to 20 characters")
	@Column(name = "MANAGER_NAME")
	private String managerName;
	
	@NotNull
	@Pattern(regexp = "^[A-Za-z0-9._]+@[A-za-z0-9]+[.][A-za-z]{2,5}$", message = "Email is not valid")
	@Column(name = "EMAIL")
	private String email;
	
	@NotNull
	@Pattern(regexp = "[A-Za-z]+[0-9]+[!@#$%^&*]+", message = "Password should atleast contain a digit and a special character")
	@Length(min = 7, max = 20, message = "Username should be from 7 to 20 characters")
	@Column(name = "PASSWORD")
	private String password;
	
	@Pattern(regexp = "[0-9]{10}", message = "Invalid mobile number")
	@Column(name = "MOBILE_NUM" )
	private String mobileNum;

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId")
	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
	private Company company;
	
	public Manager(String managerId, String managerName, String email, String password, String mobileNum, Company company) {
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
