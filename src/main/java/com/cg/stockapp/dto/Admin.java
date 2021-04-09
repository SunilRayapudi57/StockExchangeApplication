package com.cg.stockapp.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "admin")
public class Admin {
	
	@Id
	private String adminId;
	private String adminName;
	private String email;
	private String password;
	
	public Admin(String adminId, String adminName, String email, String password) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.email = email;
		this.password = password;
	}

	public Admin() {
		super();
	}
	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
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

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", email=" + email + ", password=" + password
				+ "]";
	}
	
}