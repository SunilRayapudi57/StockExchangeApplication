package com.cg.stockapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.stockapp.dto.User;
import com.cg.stockapp.exceptions.DuplicateUserException;
import com.cg.stockapp.repository.UserRepository;

@Component
public class UserDao implements IUserDao{

	@Autowired
	UserRepository repo;
	
	@Override
	public boolean addUser(User user) {
		if(repo.existsById(user.getUserId()))
			throw new DuplicateUserException("User already exists with id "+user.getUserId());
		else {
			repo.save(user);
			return true;
		}
	}

}
