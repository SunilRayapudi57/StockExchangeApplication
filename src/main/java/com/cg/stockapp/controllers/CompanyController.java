package com.cg.stockapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.stockapp.dto.Company;
import com.cg.stockapp.service.ICompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	ICompanyService serv;

	@PostMapping
	public String addCompany(@RequestBody Company company) {
		serv.addCompany(company);
		return "Company added successfully";
	}

}
