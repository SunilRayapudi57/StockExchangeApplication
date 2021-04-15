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

import com.cg.stockapp.entities.Stock;
import com.cg.stockapp.service.StockService;

@RestController
@RequestMapping("/stocks")
public class StockController {

	@Autowired
	StockService serv;

	@GetMapping
	public List<Stock> viewAllStock() {
		return serv.viewAllStock();
	}

	@GetMapping("{stockId}")
	public Stock viewStockDetails(@PathVariable("stockId") String stockId) {
		return serv.viewStockDetails(stockId);
	}

	@GetMapping("/company/{companyName}")
	public List<Stock> viewStockByCompany(@PathVariable("companyName") String companyName){
		return serv.viewStockByCompany(companyName);
	}

	@PostMapping
	public String addStock(@RequestBody Stock stock) {
		serv.addStock(stock);
		return "Stock added successfully";
	}

	@PutMapping
	public String updateStock(@RequestBody Stock stock) {
		serv.updateStockDetails(stock);
		return "Stock details updated successfully";
	}

	@DeleteMapping("{stockid}")
	public String removeStock(@PathVariable("stockid") String id) {
		serv.removeStockDetails(id);
		return "Stock Data successfully deleted";
	}

}
