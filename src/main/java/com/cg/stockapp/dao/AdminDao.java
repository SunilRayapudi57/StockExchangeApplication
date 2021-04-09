package com.cg.stockapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.stockapp.dto.Admin;
import com.cg.stockapp.exceptions.DuplicateAdminException;
import com.cg.stockapp.repository.AdminRepository;

@Component
public class AdminDao implements IAdminDao{

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
