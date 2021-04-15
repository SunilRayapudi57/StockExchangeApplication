package com.cg.stockapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.stockapp.entities.Company;
import com.cg.stockapp.entities.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, String>{
	
	public List<Stock> findByCompany(Company company);
	
//	public boolean existsbyCompany(Company company);
}
