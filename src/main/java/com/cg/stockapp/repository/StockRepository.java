package com.cg.stockapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.stockapp.entities.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, String>{

}
