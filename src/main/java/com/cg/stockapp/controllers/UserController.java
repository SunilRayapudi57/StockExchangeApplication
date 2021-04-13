package com.cg.stockapp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.stockapp.entities.User;
import com.cg.stockapp.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	IUserService serv;

	@GetMapping("/login/{userName}/{password}")
	public User userLogin(@PathVariable("userName") String username, @PathVariable("password") String password) {
		return serv.login(username, password);
	}

	@PutMapping("/logout")
	public String userLogout(@Valid @RequestBody User user, BindingResult result) {
		if (result.hasErrors())
			return "Error occured" + result;
		else
			return serv.logout(user);
	}

	@PostMapping
	public String addUser(@Valid @RequestBody User user, BindingResult result) {
		if (result.hasErrors())
			return "Error occured"+result;
		else {
			serv.addUser(user);
			return "User added successfully";
		}

	}

	@DeleteMapping("/remove/{userId}")
	public String removeUser(@PathVariable("userId") Long userId) {
		serv.removeUser(userId);
		return "User with id " + userId + " deleted successfully";
	}

	@PutMapping
	public String updateUser(@Valid @RequestBody User user) {
		serv.updateUser(user);
		return "User updated successfully";
	}

}
