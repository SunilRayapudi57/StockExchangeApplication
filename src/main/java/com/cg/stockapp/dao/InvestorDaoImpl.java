package com.cg.stockapp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.stockapp.dto.Investor;
import com.cg.stockapp.repository.InvestorRepository;

@Component
public class InvestorDaoImpl implements InvestorDao {

	@Autowired
	InvestorRepository repo;

	@Override
	public List<Investor> getAllInvestor() {
		
		return repo.findAll();
	}

	
	

	
	
	

}
