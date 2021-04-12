package com.cg.stockapp.service;

import com.cg.stockapp.entities.Manager;

public interface IManagerService {

	public boolean addManager(Manager manager);

	public boolean deleteManager(int managerId);

	public Manager getManagerDetails(int managerId);
	
	
}
