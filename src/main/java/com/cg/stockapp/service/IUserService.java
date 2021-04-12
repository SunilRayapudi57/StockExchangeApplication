package com.cg.stockapp.service;

import com.cg.stockapp.entities.User;

public interface IUserService {
	
	public boolean addUser(User user);
	
	public boolean removeUser(Long userId);
	
	public boolean updateUser(User user);
	
	public User login(String username, String password);
	
	public String logout(User user);
	
}
