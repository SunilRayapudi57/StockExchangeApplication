package com.cg.stockapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.stockapp.entities.BankAccount;
import com.cg.stockapp.entities.Investor;
import com.cg.stockapp.entities.Stock;
import com.cg.stockapp.entities.Transaction;
import com.cg.stockapp.exceptions.BankAccountNotFoundException;
import com.cg.stockapp.exceptions.CompanyNotFoundException;
import com.cg.stockapp.exceptions.DuplicateInvesterException;
import com.cg.stockapp.exceptions.EmptyTableException;
import com.cg.stockapp.exceptions.InsufficientSharesException;
import com.cg.stockapp.exceptions.InvestorNotFoundException;
import com.cg.stockapp.exceptions.StockNotFoundException;
import com.cg.stockapp.repository.CompanyRepository;
import com.cg.stockapp.repository.InvestorRepository;
import com.cg.stockapp.repository.StockRepository;
import com.cg.stockapp.repository.TransactionRepository;

@Service
public class InvestorServiceImpl implements InvestorService {

	@Autowired
	InvestorRepository investorRepo;

	@Autowired
	StockRepository stockRepo;
	
	@Autowired
	CompanyRepository companyRepo;
	
	@Autowired
	TransactionRepository transRepo;

	Logger log = LoggerFactory.getLogger(InvestorServiceImpl.class);

	//Display all the Investors
	@Override
	public List<Investor> getAllInvestor() {
		log.info("getAllInvestor() has been invoked");
		List<Investor> investorList = investorRepo.findAll();
		if (investorList.isEmpty()) {
			log.warn("EmptyTableException : No data found in the databse");
			throw new EmptyTableException("No Data Found in the database");
		} else {
			log.info("All investors returned");
			return investorList;
		}
	}

	//Add the Investor
	@Override
	public boolean addInvestor(Investor investor) {
		log.info("addInvestor() has been invoked");
		String investorId = investor.getInvestorId();
		if (investorRepo.existsById(investorId)) {
			log.warn("DuplicateInvesterException : Investor already exists with id " + investorId);
			throw new DuplicateInvesterException("Investor already exists with id " + investorId);
		} else {
			investorRepo.save(investor);
			log.info("A new Investor with id " + investorId + " has been added");
			return true;
		}
	}

	
	//Add Bank details for the Investor
	@Override
	public boolean addBankDetails(String investorId, BankAccount account) {
		log.info("addBankDetails() has been invoked");
		if (investorRepo.existsById(investorId)) {
			Investor investor = investorRepo.findById(investorId).get();
			investor.setAccount(account);
			investor.setStatus("approved");
			investorRepo.save(investor);
			log.info("Bank details has been added to the investor with id " + investorId);
			return true;
		} else {
			log.warn("InvestorNotFoundException : Update failed, Investor not found with id " + investorId);
			throw new InvestorNotFoundException("Update", "Investor not found with id " + investorId);
		}

	}

	//Display the Bank details for the Investor
	@Override
	public BankAccount getBankDetails(String investorId) {
		log.info("getBankDetails() has been invoked");
		if (investorRepo.existsById(investorId)) {
			Investor investor = investorRepo.findById(investorId).get();
			if (investor.getAccount() == null) {
				throw new BankAccountNotFoundException("Request",
						"Bank Account not found for the investor with id " + investorId);
			} else {
				return investor.getAccount();
			}
		} else {
			throw new InvestorNotFoundException("Request", "Investor not found with id " + investorId);
		}
	}

	
	//Update the Investor
	@Override
	public boolean updateInvestor(Investor investor) {
		log.info("updateInvestor() has been invoked");
		String investorId = investor.getInvestorId();
		if (investorRepo.existsById(investorId)) {
			investorRepo.save(investor);
			log.info("Investor with id " + investorId + " has been updated");
			return true;
		} else {
			log.warn("InvestorNotFoundException : Update failed, Investor not found with id " + investorId);
			throw new InvestorNotFoundException("Update", "Investor not found with id " + investorId);
		}

	}

	//Delete the Investor
	@Override
	public boolean deleteInvestor(String investorId) {
		log.info("deleteInvestor() has been invoked");
		if (investorRepo.existsById(investorId)) {
			investorRepo.deleteById(investorId);
			log.info("Investor with id " + investorId + " has been deleted");
			return true;
		} else {
			log.warn("InvestorNotFoundException : Delete failed, Investor not found with id " + investorId);
			throw new InvestorNotFoundException("Delete", "Investor not found with id " + investorId);
		}
	}

	//Display all the Investor details
	@Override
	public Investor getInvestorDetails(String investorId) {
		log.info("getInvestorDetails() has been invoked");
		if (investorRepo.existsById(investorId)) {
			return investorRepo.findById(investorId).get();
		} else {
			log.warn("InvestorNotFoundException : Request failed, Investor not found with id " + investorId);
			throw new InvestorNotFoundException("Request", "Investor not found with id " + investorId);
		}
	}

	
	//Buy stock
	@Override
	public boolean buyStock(String investorId, String stockId, int quantity) {
		log.info("buyStock() has been invoked");
		if (!investorRepo.existsById(investorId)) {
			log.warn("InvestorNotFoundException : Purchase failed, Investor not with id " + investorId);
			throw new InvestorNotFoundException("Purchase", "Investor not found with id " + investorId);
		}
		if (!stockRepo.existsById(stockId)) {
			log.warn("StockNotFoundException : Purchase failed, Stock not found with id " + stockId);
			throw new StockNotFoundException("Purchase", "Stock not found with id " + stockId);
		}

		Stock stock = stockRepo.findById(stockId).get();
		Investor investor = investorRepo.findById(investorId).get();
		int availableQuantity = stockRepo.findById(stockId).get().getQuantity();

		if (quantity > availableQuantity) {
			log.warn("InsufficientSharesException : stock with is " + stockId + " has insufficient shares");
			throw new InsufficientSharesException("This stock has insufficient shares");
		}
		
		Transaction transaction = new Transaction(LocalDateTime.now(), "Buy", quantity, investor, stock);
		investor.addTransaction(transaction);
		investorRepo.save(investor);
		transRepo.save(transaction);
		
		stock.addTransaction(transaction);
		stock.setQuantity(availableQuantity - quantity);
		stockRepo.save(stock);
		log.info("Investor " + investor.getInvestorName() + " has bought " + quantity + " shares of "
				+ stock.getCompany().getCompanyName());
		return true;
	}

	//Sell all the Stocks
	@Override
	public boolean sellAllStocks(String investorId, String stockId) {
		log.info("sellStock() has been invoked");
		if (!investorRepo.existsById(investorId)) {
			log.warn("InvestorNotFoundException : Purchase failed, Investor not with id " + investorId);
			throw new InvestorNotFoundException("Purchase", "Investor does not found with id " + investorId);
		}
		Stock stock = stockRepo.findById(stockId).get();
		Investor investor = investorRepo.findById(investorId).get();
		List<Stock> investorStocks = investor.getTransactions()
												 .stream()
												 .map(t->t.getStock())
												 .collect(Collectors.toList());
		if(!investorStocks.contains(stock)) {
			log.warn("StockNotFoundException : SellStock failed, Investor does not have a stock with id " + stockId);
			throw new StockNotFoundException("SellStock", "The investor has no stocks with id "+stockId);
		}

		int quantity = investor.getTransactions().stream()
							   .filter(transaction->transaction.getTransactionType().equals("Buy"))
							   .mapToInt(transaction->transaction.getQuantity()).sum();
	
		Transaction transaction = new Transaction(LocalDateTime.now(), "Sell", quantity, investor, stock);
		investor.addTransaction(transaction);
		investorRepo.save(investor);
		transRepo.save(transaction);
		
		stock.addTransaction(transaction);
		stock.setQuantity(stock.getQuantity()+quantity);
		stockRepo.save(stock); 
		
		log.info("Investor " + investor.getInvestorName() + " has sold " + quantity + " shares of "
				+ stock.getCompany().getCompanyName());
		return true;
	}

	
	//Display all the Investors by Stock
	@Override
	public List<Investor> viewAllInvestorByStock(String stockId) {
		log.info("viewAllInvestor() has been invoked");
		if(!stockRepo.existsById(stockId)) {
			throw new StockNotFoundException("Request", "Stock not found with id "+stockId);
		}
		
		List<Investor> investorList = transRepo.findAll().stream()
											   .filter(transaction->transaction.getStock().getStockId() == stockId)
											   .map(Transaction::getInvestor)
											   .collect(Collectors.toList());
		
		if(investorList.isEmpty()) {
			throw new InvestorNotFoundException("Request", "No investor found for the the stock with id "+stockId);
		}
		
		return investorList;
	}

	
	//Display all the Investors by Company
	@Override
	public List<Investor> viewAllInvestorByCompany(String companyId) {
		log.info("viewAllInvestor() has been invoked");
		if(!companyRepo.existsById(companyId)) {
			throw new CompanyNotFoundException("Request", "Company not found with id "+companyId);
		}
		
		List<Transaction> transactions = transRepo.findAll();
		List<Investor> investorList = transactions.stream()
									   .filter(transaction->transaction.getStock().getCompany().getCompanyId() == companyId)
									   .map(Transaction::getInvestor)
									   .collect(Collectors.toList());
		
		if(investorList.isEmpty()) {
			throw new InvestorNotFoundException("Request", "No investor found for the company with id "+companyId);
		}
		return investorList; 
	}
	
}
