package com.cg.stockapp.dao;

import com.cg.stockapp.dto.Company;

public interface ICompany {
	
	public boolean addCompany(Company company);
	public List<Company> getAllCompanyInfo();
	public Company getCompanyDetails(String companyId);
 	public boolean  updateCompanyInfo(Company info);
	public boolean deleteCompanyInfo(String companyId);
}
