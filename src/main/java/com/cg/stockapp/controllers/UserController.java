package com.cg.stockapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.stockapp.dto.User;
import com.cg.stockapp.service.IUserService;

public class UserController {
	
	@Autowired
	IUserService serv;
	
	@PostMapping
	public String addAdmin(@RequestBody User user) {
		serv.addUser(user);
		return "User added successfully";
	}
	
}
