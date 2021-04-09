package com.cg.stockapp.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.stockapp.dto.Company;
import com.cg.stockapp.exceptions.DuplicateCompanyException;
import com.cg.stockapp.repository.CompanyRepository;

public class CompanyDao implements ICompany{

	@Autowired
	CompanyRepository repo;
	
	@Override
	public boolean addCompany(Company company) {
		
		if(repo.existsById(company.getCompanyId()))
			throw new DuplicateCompanyException("Company already exists with id "+company.getCompanyId());
		else {
			repo.save(company);
			return true;
		}
	}
	
}
