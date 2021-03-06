package com.cg.stockapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.stockapp.entities.User;
import com.cg.stockapp.exceptions.DuplicateUserException;
import com.cg.stockapp.exceptions.UserNotFoundException;
import com.cg.stockapp.repository.UserRepository;

@Service
public class UserService implements IUserService{

	@Autowired
	UserRepository repo;
	
	Logger log = LoggerFactory.getLogger(UserService.class);
	
	//Add the User
	@Override
	public boolean addUser(User user) {
		log.info("addUser() invoked");
		if(repo.existsById(user.getUserId())) {
			log.warn("DuplicateUserException thrown...Creation Failed, User already exists with id "+user.getUserId());
			throw new DuplicateUserException("User already exists with id "+user.getUserId());
		} 
		else {
			repo.save(user);
			log.info("A new user has been added");
			return true;
		}
	}

	//Remove the User
	@Override
	public boolean removeUser(Long userId) {
		log.info("removeUser() invoked");
		if(repo.existsById(userId)) {
			repo.deleteById(userId);
			log.info("User with id "+userId+" removed");
			return true;
		}
		else {
			log.warn("UserNotFoundException thrown...Delete Failed, User not found with id "+userId);
			throw new UserNotFoundException("Delete","User not found with id "+userId);
		}
	}

	//Update the User
	@Override
	public boolean updateUser(User user) {
		log.info("updateUser() invoked");
		if(repo.existsById(user.getUserId())) {
			repo.save(user);
			log.info("User with id "+repo.getOne(user.getUserId()).getUserId()+" updated");
			return true;
		}
		else {
			log.warn("UserNotFoundException thrown...Delete Failed, User not found with id "+user.getUserName());
			throw new UserNotFoundException("Update","User not found with id "+user.getUserId());
		}
	}

	//Login method
	@Override
	public User login(String username, String password) {
		log.info("login() invoked");
		User user = repo.findByUserNameAndPassword(username, password);
		if(user == null) {
			log.error("UserNotFoundException thrown...Login Failed, username or password is incorrect");
			throw new UserNotFoundException("Login","username or password is incorrect");
		}
		else {
			log.info("User "+username+" has logged in successfully ");
			return user;
		}
	}

	//Logout Method
	@Override
	public String logout(User user) {
		log.info("logout() invoked");
		log.info("User "+user.getUserName()+" has been logged out");
		return "User "+user.getUserName()+" has been logged out";
	}

}
