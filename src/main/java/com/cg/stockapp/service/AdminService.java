package com.cg.stockapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.stockapp.entities.Admin;
import com.cg.stockapp.exceptions.DuplicateAdminException;
import com.cg.stockapp.repository.AdminRepository;

@Service
public class AdminService implements IAdminService{

	@Autowired
	AdminRepository repo;
	
	@Override
	public boolean addAdmin(Admin admin) {
		if(repo.existsById(admin.getAdminId()))
			throw new DuplicateAdminException("Admin already exists with id "+admin.getAdminId());
		else {
			repo.save(admin);
			return true;
		}
	}

}
