package com.cg.stockapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.stockapp.entities.Admin;
import com.cg.stockapp.exceptions.AdminNotFoundException;
import com.cg.stockapp.exceptions.DuplicateAdminException;
import com.cg.stockapp.repository.AdminRepository;

@Service
public class AdminService implements IAdminService{

	@Autowired
	AdminRepository repo;
	
	Logger log = LoggerFactory.getLogger(AdminService.class);
	
	@Override
	public boolean addAdmin(Admin admin) {
		log.info("addAdmin() has been invoked");
		if(repo.existsById(admin.getAdminId())) {
			log.warn("DuplicateAdminException thrown... Creation failed, Admin already exists with id "+admin.getAdminId());
			throw new DuplicateAdminException("Admin already exists with id "+admin.getAdminId());
		}
		else {
			repo.save(admin);
			log.info("A new Admin has been added with id "+admin.getAdminId());
			return true;
		}
	}

	@Override
	public boolean updateAdmin(Admin admin) {
		log.info("updateAdmin() has been invoked");
		if(repo.existsById(admin.getAdminId())) {
			repo.save(admin);
			log.info("Admin with id "+admin.getAdminId()+" has been updated");
			return true;
		}
		else {
			throw new AdminNotFoundException("Update", "Admin not found with id "+admin.getAdminId());
		}
			
	}

	@Override
	public Admin viewAdmin(String adminId) {
		log.info("viewAdmin() has been invoked");
		if(repo.existsById(adminId)) {
			log.info("Admin with id "+adminId+"has been returned");
			return repo.findById(adminId).get();
		}
		else {
			log.warn("AdminNotFoundException thrown... Request failed, Admin not found with id "+adminId);
			throw new AdminNotFoundException("Request", "Admin not found with id "+adminId);
		}
	}

	@Override
	public boolean removeAdmin(String adminId) {
		if(repo.existsById(adminId)) {
			repo.deleteById(adminId);
			return true;
		}
		else {
			log.warn("AdminNotFoundException thrown... Delete failed, Admin not found with id "+adminId);
			throw new AdminNotFoundException("Delete", "Admin not found with id "+adminId);
		}
	}

}
