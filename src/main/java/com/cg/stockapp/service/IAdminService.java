package com.cg.stockapp.service;

import com.cg.stockapp.entities.Admin;

public interface IAdminService {
	
	public boolean addAdmin(Admin admin);
	
	public boolean updateAdmin(Admin admin);
	
	public Admin viewAdmin(String adminId);
	
	public boolean removeAdmin(String adminId);
	
}
