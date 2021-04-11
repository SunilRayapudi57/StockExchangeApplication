package com.cg.stockapp.service;

import java.util.List;

import com.cg.stockapp.dto.Manager;

public interface ManagerService {
	
	public boolean addManager(Manager man);
	
	
	public List<ManagerService> getAllManager(int ManagerId);
	
	
	public Manager updateManager(Manager info);
	public Manager deleteManager(Manager man);

	//public int getManagerDetails(int ManagerId);
}
