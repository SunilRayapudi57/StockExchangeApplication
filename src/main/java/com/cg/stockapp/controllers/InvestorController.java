package com.cg.stockapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.stockapp.entities.Investor;
import com.cg.stockapp.service.InvestorSerive;

@RestController
@RequestMapping("/investor")
public class InvestorController {

	@Autowired
	InvestorSerive serv;

	@GetMapping
	public List<Investor> getAllInvestor() {

		return serv.getAllInvestor();
		
	}

	@PostMapping
	public String addInvestor(@RequestBody Investor inv) {
		serv.addInvestor(inv);
		return "Investor added successfully";
	}

	@DeleteMapping
	public String deleteInvestor(@RequestBody Investor inv) {
		serv.deleteInvestor(inv);
		return "Investor with id "+inv.getInvestorId()+" successfully deleted";
	}
	
}
