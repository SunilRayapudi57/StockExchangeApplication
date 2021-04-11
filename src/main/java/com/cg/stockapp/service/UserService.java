package com.cg.stockapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.stockapp.entities.User;
import com.cg.stockapp.exceptions.DuplicateUserException;
import com.cg.stockapp.repository.UserRepository;

@Service
public class UserService implements IUserService{

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
