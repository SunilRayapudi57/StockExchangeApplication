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
import com.cg.stockapp.service.ICompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	ICompanyService serv;

	@PostMapping
	public String addCompany(@Valid @RequestBody Company company) {
		serv.addCompany(company);
		return "Company added successfully";
	} 
	
	@GetMapping("{companyId}")
	public Company getCompanyInfo(@PathVariable("companyId") String companyId) {
		return serv.getCompanyInfo(companyId);
	}
	
	@GetMapping()
	public List<Company> getAllCompanyInfo(){
		return serv.getAllCompanyInfo();
	}
	
	@PutMapping
	public String updateCompany(@Valid @RequestBody Company company) {
		serv.updateCompany(company);
		return "Company with id"+company.getCompanyId();
	}
	
	@DeleteMapping("{companyId}")
	public String deleteCompany(@PathVariable("companyId") String companyId) {
		serv.deleteCompany(companyId);
		return "Company with id "+companyId+" deleted successfully";
	}

}
