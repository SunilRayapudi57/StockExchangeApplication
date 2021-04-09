package com.cg.stockapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.stockapp.dao.UserDao;
import com.cg.stockapp.dto.User;

@Service
public class UserService implements IUserService{

	
	@Autowired
	UserDao dao;
	
	@Override
	public boolean addUser(User user) {
		dao.addUser(user);
		return true;
	}

}
