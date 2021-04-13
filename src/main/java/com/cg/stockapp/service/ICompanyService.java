package com.cg.stockapp.service;

import java.util.List;

import com.cg.stockapp.entities.Company;

public interface ICompanyService {
	
	public boolean addCompany(Company company);
	
	public Company getCompanyInfo(String companyId);
	
	public List<Company> getAllCompanyInfo();
	
	public boolean deleteCompany(String companyId);
	
	public boolean updateCompany(Company company);
	
	
}
