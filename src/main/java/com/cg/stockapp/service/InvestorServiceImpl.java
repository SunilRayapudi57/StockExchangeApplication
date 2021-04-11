package com.cg.stockapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.stockapp.entities.Investor;
import com.cg.stockapp.exceptions.DuplicateInvesterException;
import com.cg.stockapp.exceptions.EmptyTableException;
import com.cg.stockapp.exceptions.InvestorNotFoundException;
import com.cg.stockapp.repository.InvestorRepository;

@Service
public class InvestorServiceImpl implements InvestorSerive{

	@Autowired
	InvestorRepository repo;

	@Override
	public List<Investor> getAllInvestor() {

		if(repo.findAll().isEmpty())
			throw new EmptyTableException("No Data Found in the database");
		else
			return repo.findAll();
	}

	@Override
	public boolean addInvestor(Investor inv) {
		if (repo.existsById(inv.getInvestorId()))
			throw new DuplicateInvesterException("Investor already exists with id " + inv.getInvestorId());
		else {
			repo.save(inv);
			return true;
		}
	}

	@Override
	public boolean deleteInvestor(Investor inv) {
		if (repo.existsById(inv.getInvestorId())) {
			repo.delete(inv);
			return true;
		} else
			throw new InvestorNotFoundException("Investor not found with id " + inv.getInvestorId());
	}

}
