package com.cg.stockapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.stockapp.dao.ManagerDao;
import com.cg.stockapp.dto.Manager;

@Service
public class ManagerService implements IManagerService{

	
	@Autowired
	ManagerDao dao;
	@Override
	public boolean addManager(Manager manager) {
		dao.addManager(manager);
		return true;
	}
}
