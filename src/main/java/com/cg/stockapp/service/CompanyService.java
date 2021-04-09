package com.cg.stockapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.stockapp.dao.CompanyDao;
import com.cg.stockapp.dto.Company;

@Service
public class CompanyService implements ICompanyService{

	@Autowired
	CompanyDao dao;
	
	@Override
	public boolean addCompany(Company company) {
		dao.addCompany(company);
		return true;
	}

}
