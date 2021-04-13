package com.cg.stockapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.stockapp.entities.Company;
import com.cg.stockapp.entities.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, String>{
	
	public Manager findByCompany(Company company);
	
}
