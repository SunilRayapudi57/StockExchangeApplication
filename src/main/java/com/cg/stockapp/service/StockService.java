package com.cg.stockapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.stockapp.entities.Stock;
import com.cg.stockapp.exceptions.DuplicateStockException;
import com.cg.stockapp.exceptions.EmptyTableException;
import com.cg.stockapp.exceptions.StockNotFoundException;
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
		if (repo.existsById(stock.getStockId())) {
			repo.save(stock);
			return true;
		}
		else
			throw new StockNotFoundException("Update", "Stock not found with id "+stock.getStockId());
	}

	@Override
	public boolean removeStockDetails(String stockId) {
		if (repo.existsById(stockId)) {
			repo.deleteById(stockId);
			return true;
		}
		else
			throw new StockNotFoundException("Delete", "Stock not found with id "+stockId);
	}

}
