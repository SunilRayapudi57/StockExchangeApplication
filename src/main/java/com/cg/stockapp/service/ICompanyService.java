package com.cg.stockapp.service;

import com.cg.stockapp.entities.Company;

public interface ICompanyService {
	
	public boolean addCompany(Company company);
	
	public Company getCompanyDetails(String companyId);
	
	public boolean deleteCompany(String companyId);
	
}
