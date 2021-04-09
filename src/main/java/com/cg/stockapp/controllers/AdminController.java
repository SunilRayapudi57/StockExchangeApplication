package com.cg.stockapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.stockapp.dto.Admin;
import com.cg.stockapp.service.IAdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	IAdminService serv;
	
	@PostMapping
	public String addAdmin(@RequestBody Admin admin) {
		serv.addAdmin(admin);
		return "Admin added successfully";
	}
	
}
