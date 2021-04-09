package com.cg.stockapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.stockapp.dao.IAdminDao;
import com.cg.stockapp.dto.Admin;

@Service
public class AdminService implements IAdminService{

	@Autowired
	IAdminDao dao;
	
	@Override
	public boolean addAdmin(Admin admin) {
		dao.addAdmin(admin);
		return true;
	}

}
