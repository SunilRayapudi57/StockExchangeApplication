package com.cg.stockapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.stockapp.entities.Company;
import com.cg.stockapp.exceptions.CompanyNotFoundException;
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

	@Override
	public Company getCompanyDetails(String companyId) {
		if(repo.existsById(companyId))
			return repo.findById(companyId).get();
		else
			throw new CompanyNotFoundException("Request", "Company not found with id "+companyId);
	}

	@Override
	public boolean deleteCompany(String companyId) {
		if(repo.existsById(companyId)) {
			repo.deleteById(companyId);
			return true;
		}
		else
			throw new CompanyNotFoundException("Delete", "Company not found with id "+companyId);
	}

}
