package com.cg.stockapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping
	public String addStock(@RequestBody Stock stock) {
		serv.addStock(stock);
		return "Stock added successfully";
	}
	
}
