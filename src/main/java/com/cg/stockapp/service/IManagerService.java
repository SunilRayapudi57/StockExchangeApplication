package com.cg.stockapp.service;

import java.util.List;

import com.cg.stockapp.entities.Company;
import com.cg.stockapp.entities.Manager;

public interface IManagerService {

	public boolean addManager(Manager manager);
	
	public List<Manager> getAllManager();
	
	public Manager getManagerDetails(String managerId);
	
	public boolean updateManager(Manager manager);

	public boolean deleteManager(String managerId);
	
	public Manager getManager(Company company);
	
}
