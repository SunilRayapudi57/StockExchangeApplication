package com.cg.stockapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.stockapp.entities.BankAccount;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long>{

}
