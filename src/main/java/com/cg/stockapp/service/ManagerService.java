package com.cg.stockapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.stockapp.entities.Manager;
import com.cg.stockapp.exceptions.DuplicateManagerException;
import com.cg.stockapp.exceptions.ManagerNotFoundException;
import com.cg.stockapp.repository.ManagerRepository;

@Service
public class ManagerService implements IManagerService {

	@Autowired
	ManagerRepository repo;
	
	@Override
	public Manager getManagerDetails(int managerId) {
		if(repo.existsById(managerId)) {
			return repo.findById(managerId).get();
		}
		else
			throw new ManagerNotFoundException("Request", "Manager not found with id " + managerId);
	}

	@Override
	public boolean addManager(Manager manager) {
		if (repo.existsById(manager.getManagerId()))
			throw new DuplicateManagerException("Manager already exists with id " + manager.getManagerId());
		else {
			repo.save(manager);
			return true;
		}
	}

	@Override
	public boolean deleteManager(int managerId) {
		if (repo.existsById(managerId)) {
			repo.deleteById(managerId);
			return true;
		} else
			throw new ManagerNotFoundException("Delete", "Manager not found with id " + managerId);
	}
	
	
}
