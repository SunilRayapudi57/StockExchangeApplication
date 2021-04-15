package com.cg.stockapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.stockapp.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

}
