package com.cg.stockapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.stockapp.entities.Stock;
import com.cg.stockapp.exceptions.DuplicateStockException;
import com.cg.stockapp.exceptions.EmptyTableException;
import com.cg.stockapp.repository.StockRepository;

@Service
public class StockService implements IStockService {

	@Autowired
	StockRepository repo;

	@Override
	public boolean addStock(Stock stock) {
		if (repo.existsById(stock.getStockId()))
			throw new DuplicateStockException("Stock already exists with id " + stock.getStockId());
		else {
			repo.save(stock);
			return true;
		}
	}

	@Override
	public List<Stock> viewAllStock() {

		if (repo.findAll().isEmpty())
			throw new EmptyTableException("No Data Found in the database");
		else
			return repo.findAll();
	}
	
	@Override
	public boolean updateStockDetails(Stock stock) {
		if(repo.existsById(stock.getStockId())) {
			repo.save(stock);
		return true;
		}
		return false;
	}

	@Override
	public boolean removeStockDetails(int stid) {
		if(repo.existsById(stid)) {
			repo.deleteById(stid);
		return true;
		}
			return false;
	}


}
