package com.cg.stockapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.stockapp.entities.Investor;
import com.cg.stockapp.service.InvestorService;

@RestController
@RequestMapping("/investor")
public class InvestorController {

	@Autowired
	InvestorService serv;

	@GetMapping
	public List<Investor> getAllInvestor() {
		return serv.getAllInvestor();
	}
	
	@GetMapping("{investorId}")
	public Investor getInvestorDetails(@PathVariable("investorId") String id) {
		return serv.getInvestorDetails(id);
	}

	@PostMapping
	public String addInvestor(@RequestBody Investor investor) {
		serv.addInvestor(investor);
		return "Investor added successfully";
	}

	@DeleteMapping("{investorId}")
	public String deleteInvestor(@PathVariable("investorId") String id) {
		serv.deleteInvestor(id);
		return "Investor with id "+id+" deleted successfully";
	}
	
	@PutMapping
	public String updateInvestor(@RequestBody Investor investor) {
		serv.updateInvestor(investor);
		return "Investor details updated successfully";
	}
	
}
