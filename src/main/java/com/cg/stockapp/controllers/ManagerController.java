package com.cg.stockapp.controllers;

import java.util.List;

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

import com.cg.stockapp.entities.Company;
import com.cg.stockapp.entities.Manager;
import com.cg.stockapp.service.ManagerService;

@RestController
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private ManagerService serv;

	@PostMapping
	public String addManager(@Valid @RequestBody Manager manager) {
		serv.addManager(manager);
		return "Manager added successfully";
	}

	@GetMapping("{managerId}")
	public Manager getManagerDetails(@PathVariable("managerId") String managerId) {
		return serv.getManagerDetails(managerId);
	}
	
	@PutMapping("/company")
	public Manager getManager(@Valid @RequestBody Company company) {
		return serv.getManager(company);
	}
	
	@GetMapping
	public List<Manager> getAllManager(){
		return serv.getAllManager();
	}
	
	@PutMapping
	public String updateManager(@Valid @RequestBody Manager manager) {
		serv.updateManager(manager);
		return "Manager with id "+manager.getManagerId()+" updated successfully";
 	}
	
	@DeleteMapping("{managerId}")
	public String deleteManager(@PathVariable String managerId) {
		serv.deleteManager(managerId);
		return "Manager with id "+managerId+" has been deleted successfully";
	}

}