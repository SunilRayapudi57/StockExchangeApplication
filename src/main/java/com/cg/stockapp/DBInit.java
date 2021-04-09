package com.cg.stockapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cg.stockapp.dto.Admin;
import com.cg.stockapp.dto.BankAccount;
import com.cg.stockapp.dto.Company;
import com.cg.stockapp.dto.Investor;
import com.cg.stockapp.dto.Manager;
import com.cg.stockapp.dto.Stock;
import com.cg.stockapp.dto.User;
import com.cg.stockapp.repository.AdminRepository;
import com.cg.stockapp.repository.BankAccountRepository;
import com.cg.stockapp.repository.CompanyRepository;
import com.cg.stockapp.repository.InvestorRepository;
import com.cg.stockapp.repository.ManagerRepository;
import com.cg.stockapp.repository.StockRepository;
import com.cg.stockapp.repository.UserRepository;

//@Component
public class DBInit implements CommandLineRunner{

	@Autowired
	AdminRepository adminRepo;
	
	@Autowired
	UserRepository userRepo;
		
	@Autowired
	BankAccountRepository accountRepo;
	BankAccount acc = new BankAccount(1234567, "HDFC", "Chennai", "HDFC000527");
	
//	@Autowired
//	InvestorRepository investorRepo;
//	Investor inv = new Investor(1, "Kavitha", "kavitha.a@gmain.com", "kavitha#257", "1234567890", "Female", acc, "notApproved");
//	
//	@Autowired
//	CompanyRepository companyRepo;
//	Company company = new Company("AMZ", "Amazon", "NSE");
//	
//	@Autowired
//	ManagerRepository managerRepo;
//	Manager manager = new Manager(1, "David", "david.v@gmain.com", "david#123", "987643210", company);
//	
//	@Autowired
//	StockRepository stockRepo;

	@Override
	public void run(String... args) throws Exception {
		
		adminRepo.save(new Admin("A100", "Ramana" , "ramana@gmail.com", "ramana#123"));
		userRepo.save(new User(12345L, "ramana52", "ram#qwertyuiop", "Admin"));
		accountRepo.save(acc);
//		investorRepo.save(inv);
//		companyRepo.save(company);
//		stockRepo.save(new Stock(1, "AmazonS1", inv, company, 140, "E-Commerce", 214.75, 42, "active"));
		
	}

}
