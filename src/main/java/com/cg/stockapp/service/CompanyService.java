package com.cg.stockapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.stockapp.entities.Company;
import com.cg.stockapp.exceptions.DuplicateCompanyException;
import com.cg.stockapp.repository.CompanyRepository;

@Service
public class CompanyService implements ICompanyService{

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
