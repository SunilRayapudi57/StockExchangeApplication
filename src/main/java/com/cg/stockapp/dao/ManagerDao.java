package com.cg.stockapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.stockapp.dto.Manager;
import com.cg.stockapp.exceptions.DuplicateManagerException;
import com.cg.stockapp.repository.ManagerRepository;

@Component
public class ManagerDao implements IManagerDao {

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

}
