package com.cg.stockapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.stockapp.entities.Investor;

@Repository
public interface InvestorRepository extends JpaRepository<Investor, String>{

}
