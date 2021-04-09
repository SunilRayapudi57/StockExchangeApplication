package com.cg.stockapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.stockapp.dto.Manager;
import com.cg.stockapp.service.ManagerService;

@RestController
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private ManagerService serv;

	@PostMapping
	public String addManager(@RequestBody Manager manager) {
		serv.addManager(manager);
		return "Manager added successfully";

	}

}