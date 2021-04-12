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
public class InvestorServiceImpl implements InvestorService{

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
	public boolean deleteInvestor(String investorId) {
		if (repo.existsById(investorId)) {
			repo.deleteById(investorId);
			return true;
		} else
			throw new InvestorNotFoundException("Delete", "Investor not found with id " + investorId);
	}

	@Override
	public Investor getInvestorDetails(String investorId) {
		Investor investor = repo.findById(investorId).get();
		if(investor == null)
			throw new  InvestorNotFoundException("Request", "Investor not found with id "+investorId);
		else
			return investor;
	}

	@Override
	public boolean updateInvestor(Investor info) {
		if(repo.existsById(info.getInvestorId())) {
			repo.save(info);
			return true;
		}
		else
			throw new InvestorNotFoundException("Update", "Investor not found with id "+info.getInvestorId());

	}

}
