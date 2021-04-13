package com.cg.stockapp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.stockapp.entities.Admin;
import com.cg.stockapp.service.IAdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	IAdminService serv;
	
	@GetMapping("{adminId}")
	public Admin viewAdmin(@PathVariable("adminId") String adminId) {
		return serv.viewAdmin(adminId);
	}
	
	@PutMapping
	public String updateAdmin(@RequestBody Admin admin) {
		serv.updateAdmin(admin);
		return "Admin with id "+admin.getAdminId()+" updated successfully";
	}
	
	@PostMapping
	public String addAdmin(@RequestBody Admin admin) {
		serv.addAdmin(admin);
		return "Admin added successfully";
	}
	
	@DeleteMapping("{adminId}")
	public String removeAdmin(@PathVariable("adminId") String adminId) {
		serv.removeAdmin(adminId);
		return "Admin with id "+adminId+" has been removed successfully";
	}
	
}
