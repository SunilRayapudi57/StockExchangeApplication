package com.cg.stockapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.stockapp.entities.Company;
import com.cg.stockapp.entities.Manager;
import com.cg.stockapp.exceptions.DuplicateManagerException;
import com.cg.stockapp.exceptions.EmptyTableException;
import com.cg.stockapp.exceptions.ManagerNotFoundException;
import com.cg.stockapp.repository.ManagerRepository;

@Service
public class ManagerService implements IManagerService {

	@Autowired
	ManagerRepository repo;
	
	Logger log = LoggerFactory.getLogger(ManagerService.class);
	
	@Override
	public Manager getManagerDetails(String managerId) {
		log.info("getManagerDetails() has been invoked");
		if(repo.existsById(managerId)) {
			log.info("Manager with id "+managerId+" has been returned");
			return repo.findById(managerId).get();
		}
		else {
			log.warn("ManagerNotFoundException : Request falied, Manger not found with id "+managerId);
			throw new ManagerNotFoundException("Request", "Manager not found with id " + managerId);
		}
	}

	@Override
	public boolean addManager(Manager manager) {
		log.info("addManager() has been invoked");
		if (repo.existsById(manager.getManagerId())) {
			log.warn("DuplicateManagerException : Creation failed, Manager already exists with id "+manager.getManagerId());
			throw new DuplicateManagerException("Manager already exists with id " + manager.getManagerId());
		}
		else {
			repo.save(manager);
			log.info("Manager with id "+manager.getManagerId()+" has been added");
			return true;
		}
	}

	@Override
	public boolean deleteManager(String managerId) {
		log.info("deleteManager() has been invoked");
		if (repo.existsById(managerId)) {
			repo.deleteById(managerId);
			log.info("Manager with id "+managerId+" has been deleted");
			return true;
		} else {
			log.warn("ManagerNotFoundException : Delete failed, Manager not found with id "+managerId);
			throw new ManagerNotFoundException("Delete", "Manager not found with id " + managerId);
		}
	}

	@Override
	public List<Manager> getAllManager() {
		log.info("getAllManager() has been invoked");
		List<Manager> managerList = repo.findAll();
		if(managerList.isEmpty()) {
			log.warn("EmptyTableException : No data found in the database");
			throw new EmptyTableException("No Data Found in the database");
		}
		else {
			log.info("All managers have been returned");
			return managerList;
		}
	}

	@Override
	public boolean updateManager(Manager manager) {
		log.info("updateManager() has been invoked");
		if (repo.existsById(manager.getManagerId())) {
			repo.save(manager);
			log.info("Manager with id "+manager.getManagerId()+" has been updated");
			return true;
		} else {
			log.warn("ManagerNotFoundException : Update failed, Manager not found with id "+manager.getManagerId());
			throw new ManagerNotFoundException("Update", "Manager not found with id " + manager.getManagerId());
		}
	}

	@Override
	public Manager getManager(Company company) {
		log.info("getManager() has been invoked");
		Manager manager= repo.findByCompany(company);
		if(manager == null) {
			log.warn("ManagerNotFoundException : Request failed, Manager not found for the company "+company.getCompanyName());
			throw new ManagerNotFoundException("Request", "Manager not found for the company "+company.getCompanyName());
		}
		else {
			log.info("Manager of company "+company.getCompanyName()+" has been returned");
			return manager;
		}
	}
	
	
}
