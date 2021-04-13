
package com.cg.stockapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID", updatable = false, nullable = false )
	private Long userId;
	
//	@NotNull
//	@Size(min = 5, max = 20, message = "Name should be 5 to 20 characters")
//	@Pattern(regexp = "^[A-Za-z]+[A-Za-z_0-9]*$", message = "Username should not contain any special characters")
	@Column(name = "USER_NAME")
	private String userName;
	
//	@NotNull
//	@Pattern(regexp = "[A-Za-z]+[0-9]+[!@#$%^&*]+", message = "Password should atleast contain a digit and a special character")
//	@Size(min = 5, max = 20, message = "Password should be 5 to 20 characters")
	@Column(name = "PASSWORD")
	private String password;
	
//	@NotNull
//	@Pattern(regexp = "(Admin|Investor|Manager)", message = "Role must be admin or investor or manager")
	@Column(name = "ROLE")
	private String role;// admin or investor or manager

	public User(Long userId, String userName, String password, String role) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	public User() {
		super();
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", role=" + role + "]";
	}
}