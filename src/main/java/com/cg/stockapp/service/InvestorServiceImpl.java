package com.cg.stockapp.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.stockapp.entities.BankAccount;
import com.cg.stockapp.entities.Company;
import com.cg.stockapp.entities.Investor;
import com.cg.stockapp.entities.Stock;
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

@Service
public class InvestorServiceImpl implements InvestorService {

	@Autowired
	InvestorRepository investorRepo;

	@Autowired
	StockRepository stockRepo;
	
	@Autowired
	CompanyRepository companyRepo;

	Logger log = LoggerFactory.getLogger(InvestorServiceImpl.class);

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
		investor.addStock(stock, quantity);
		investorRepo.save(investor);
		stock.addInvestor(investor);
		stock.setQuantity(availableQuantity - quantity);
		stockRepo.save(stock);
		log.info("Investor " + investor.getInvestorName() + " has bought " + quantity + " shares of "
				+ stock.getCompany().getCompanyName());
		return true;
	}

	@Override
	public boolean sellStock(String investorId, String stockId, int quantity) {
		log.info("sellStock() has been invoked");
		if (!investorRepo.existsById(investorId)) {
			log.warn("InvestorNotFoundException : Purchase failed, Investor not with id " + investorId);
			throw new InvestorNotFoundException("Purchase", "Investor not found with id " + investorId);
		}
		Stock stock = stockRepo.findById(stockId).get();
		Investor investor = investorRepo.findById(investorId).get();
		if (!investor.getStocks().contains(stock)) {
			log.warn("StockNotFoundException : SellStock failed, Investor does not have a stock with id " + stockId);
			throw new StockNotFoundException("SellStock", "Investor does not have a stock with id " + stockId);
		}

		
		int investorAvailableQuantity = investor.getStockQuantity().get(stock);

		if (quantity > investorAvailableQuantity) {
			log.warn("InsufficientSharesException : stock with is " + stockId + " has insufficient shares");
			throw new InsufficientSharesException("This stock has insufficient shares");
		}

		investorAvailableQuantity -= quantity;
		if (investorAvailableQuantity == 0) {
			investor.removeStock(stock);
		}
		else {
			investor.updateStock(stock, investorAvailableQuantity);
		}
		investorRepo.save(investor);

		if (investorAvailableQuantity == 0)
			stock.removeInvestor(investor);

		int stockAvailableQuantity = stockRepo.findById(stockId).get().getQuantity();
		stock.setQuantity(stockAvailableQuantity + quantity);
		stockRepo.save(stock);
		log.info("Investor " + investor.getInvestorName() + " has sold " + quantity + " shares of "
				+ stock.getCompany().getCompanyName());
		return true;
	}

	@Override
	public List<Investor> viewAllInvestorByStock(String stockId) {
		log.info("viewAllInvestor() has been invoked");
		if(!stockRepo.existsById(stockId)) {
			throw new StockNotFoundException("Request", "Stock not found with id "+stockId);
		}
		
		List<Investor> investorList = stockRepo.findById(stockId).get().getInvestors();
		
		if(investorList.isEmpty()) {
			throw new InvestorNotFoundException("Request", "No investor found for the the stock with id "+stockId);
		}
		
		return investorList;
	}
//
//	@Override
//	public List<Investor> viewAllInvestorByCompany(String companyId) {
//		log.info("viewAllInvestor() has been invoked");
//		if(!companyRepo.existsById(companyId)) {
//			throw new CompanyNotFoundException("Request", "Company not found with id "+companyId);
//		}
//		Company company = companyRepo.findById(companyId).get();
//		if(!stockRepo.existsbyCompany(company)) {
//			throw new StockNotFoundException("Request", "No stocks found for the company with id "+companyId);
//		}
//		List<Stock> stockList = stockRepo.findByCompany(company);
//		List<Investor> investorList = new ArrayList<>();
//		stockList.stream().filter(stock->!stock.getInvestors().isEmpty())
//						  .peek(stock->investorList.addAll(stock.getInvestors()));
//		if(investorList.isEmpty()) {
//			throw new InvestorNotFoundException("Request", "No investor found for the company with id "+companyId);
//		}
//		return investorList;
//						  
//	}
	
	

}
