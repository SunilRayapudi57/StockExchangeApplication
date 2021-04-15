package com.cg.stockapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.stockapp.entities.Company;
import com.cg.stockapp.exceptions.CompanyNotFoundException;
import com.cg.stockapp.exceptions.DuplicateCompanyException;
import com.cg.stockapp.exceptions.EmptyTableException;
import com.cg.stockapp.repository.CompanyRepository;

@Service
public class CompanyService implements ICompanyService {

	@Autowired
	CompanyRepository repo;

	Logger log = LoggerFactory.getLogger(CompanyService.class);

	//Adding the Company
	@Override
	public boolean addCompany(Company company) {

		log.info("addCompany() has been invoked");
		if (repo.existsById(company.getCompanyId())) {
			log.warn("DuplicateCompanyException : Company already exists with id " + company.getCompanyId());
			throw new DuplicateCompanyException("Company already exists with id " + company.getCompanyId());
		} else {
			repo.save(company);
			log.info("A new Company added");
			return true;
		}
	}

	//Display the company info
	@Override
	public Company getCompanyInfo(String companyId) {
		log.info("getCompanyInfo() has been invoked");
		if (repo.existsById(companyId)) {
			log.info("Company returned with id " + companyId);
			return repo.findById(companyId).get();
		} else {
			log.warn("CompanyNotFoundException : Request failed, Company not found with id " + companyId);
			throw new CompanyNotFoundException("Request", "Company not found with id " + companyId);
		}
	}

	//Display all the companies info
	@Override
	public List<Company> getAllCompanyInfo() {
		List<Company> companyList = repo.findAll();
		if(companyList.isEmpty()) {
			log.warn("No data found in the database");
			throw new EmptyTableException("No Data Found in the database");
		}
		else {
			log.info("All companies info returned");
			return companyList;
		}
	}
	
	//Update the Company
	@Override
	public boolean updateCompany(Company company) {
		log.info("updateCompany() has been invoked");
		if (repo.existsById(company.getCompanyId())) {
			repo.save(company);
			log.info("Company updated with id "+company.getCompanyId());
			return true;
		} else {
			log.warn("CompanyNotFoundException : Update failed, Company not found with id "+company.getCompanyId());
			throw new CompanyNotFoundException("Update", "Company not found with id " + company.getCompanyId());
		}
	}
	
	//Delete the company
	@Override
	public boolean deleteCompany(String companyId) {
		log.info("deleteCompany() has been invoked");
		if (repo.existsById(companyId)) {
			repo.deleteById(companyId);
			log.info("Company deleted with id "+companyId);
			return true;
		} else {
			log.warn("CompanyNotFoundException : Delete failed, Company not found with id "+companyId);
			throw new CompanyNotFoundException("Delete", "Company not found with id " + companyId);
		}
	}


	

}
