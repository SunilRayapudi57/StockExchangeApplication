package com.cg.stockapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.stockapp.entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String>{

}
