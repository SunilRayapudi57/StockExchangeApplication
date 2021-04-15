package com.cg.stockapp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.stockapp.entities.BankAccount;
import com.cg.stockapp.entities.Investor;
import com.cg.stockapp.service.InvestorService;

@RestController
@RequestMapping("/investor")
public class InvestorController {

	@Autowired
	InvestorService serv;

	//get all the investors
	@GetMapping
	public List<Investor> getAllInvestor() {
		return serv.getAllInvestor();
	}

	@GetMapping("{investorId}")
	public Investor getInvestorDetails(@PathVariable("investorId") String id) {
		return serv.getInvestorDetails(id);
	}

	@GetMapping("/bankDetails/{investorId}")
	public BankAccount getBankDetails(@PathVariable("investorId") String investorId) {
		return serv.getBankDetails(investorId);
	}
	
	@GetMapping("/stock/{stockId}")
	public List<Investor> viewAllInvestorByStock(@PathVariable("stockId") String stockId){
		return serv.viewAllInvestorByStock(stockId);
	}

	@GetMapping("/company/{companyId}")
	public List<Investor> viewAllInvestorByCompany(@PathVariable("companyId") String companyId){
		return serv.viewAllInvestorByCompany(companyId);
	}
	
	@PostMapping
	public String addInvestor(@Valid @RequestBody Investor investor) {
		serv.addInvestor(investor);
		return "Investor added successfully";
	}

	@DeleteMapping("{investorId}")
	public String deleteInvestor(@PathVariable("investorId") String id) {
		serv.deleteInvestor(id);
		return "Investor with id " + id + " deleted successfully";
	}

	@PutMapping("{investorId}")
	public String addBankDetails(@PathVariable("investorId") String investorId, @Valid @RequestBody BankAccount account) {
		serv.addBankDetails(investorId, account);
		return "Bank details added successfully for the investor with id " + investorId;
	}

	@PutMapping
	public String updateInvestor(@Valid @RequestBody Investor investor) {
		serv.updateInvestor(investor);
		return "Investor details updated successfully";
	}

	@PutMapping("buyStock/{investorId}/{stockId}/{quantity}")
	public String buyStock(
			@PathVariable("investorId")String investorId,
			@PathVariable("stockId") String stockId,
			@PathVariable("quantity") int quantity
		) {
		serv.buyStock(investorId, stockId, quantity);
		return quantity+" shares of the stock with id "+stockId+" has been purchased by investor with id "+investorId;
	}
	
	@PutMapping("sellStock/{investorId}/{stockId}")
	public String sellStock(
			@PathVariable("investorId")String investorId,
			@PathVariable("stockId") String stockId
		) {
		serv.sellAllStocks(investorId, stockId);
		return "All shares with id "+stockId+" has been sold by investor with id "+investorId;
	}
	
}
