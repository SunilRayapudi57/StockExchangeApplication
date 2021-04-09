package com.cg.stockapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.stockapp.dao.InvestorDao;
import com.cg.stockapp.dto.Investor;

@Service
public class InvestorServiceImpl implements InvestorSerive{

	@Autowired
	InvestorDao dao;
	
	@Override
	public List<Investor> getAllInvestor() {

		return dao.getAllInvestor();
		
	}

}
