package com.cg.stockapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.stockapp.entities.Manager;
import com.cg.stockapp.exceptions.DuplicateManagerException;
import com.cg.stockapp.exceptions.ManagerNotFoundException;
import com.cg.stockapp.repository.ManagerRepository;

@Service
public class ManagerService implements IManagerService{

	
	@Autowired
	ManagerRepository repo;

	@Override
	public boolean addManager(Manager manager) {
		if (repo.existsById(manager.getManagerId()))
			throw new DuplicateManagerException("Manager already exists with id " + manager.getManagerId());
		else {
			repo.save(manager);
			return true;
		}
	}

	public boolean deleteManager(Manager manager) {
		if (repo.existsById(manager.getManagerId())) {
			repo.deleteById(manager.getManagerId());
			return true;
		} else
			throw new ManagerNotFoundException("Manager not found with id " + manager.getManagerId());

	}
}
