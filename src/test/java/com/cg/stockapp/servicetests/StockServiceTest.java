package com.cg.stockapp.servicetests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.stockapp.entities.Company;
import com.cg.stockapp.entities.Stock;
import com.cg.stockapp.repository.StockRepository;
import com.cg.stockapp.service.StockService;

@SpringBootTest
class StockServiceTest {

	@Autowired
	private StockService stockService;

	@MockBean
	private StockRepository stockRepository;

	@Test
	@DisplayName("Test for adding stock")
	public void addStockTest() {
		Company company = new Company("AMZN", "Amazon inc.", "E-Commerce");
		Stock stock = new Stock("AMZN01", "Amazon", 50, "NSE", 1475, company);
		when(stockRepository.save(stock)).thenReturn(stock);
		assertEquals(true, stockService.addStock(stock));
	}

	@Test
	@DisplayName("Test for updating stock")
	public void updateCompanyTest() {
		Company company = new Company("AMZN", "Amazon inc.", "E-Commerce");
		Stock stock = new Stock("AMZN01", "Amazon", 50, "NSE", 1475, company);
		when(stockRepository.save(stock)).thenReturn(stock);
		stock.setStockName("Silver");
		assertThat(stockRepository.findById(stock.getStockId())).isNotEqualTo(stock);
	}

	@Test
	@DisplayName("Test for deleting stock")
	public void removeCompanyTest() {
		Company company = new Company("AMZN", "Amazon inc.", "E-Commerce");
		Stock stock = new Stock("AMZN01", "Amazon", 50, "NSE", 1475, company);
		when(stockRepository.existsById(stock.getStockId())).thenReturn(true);
		stockService.removeStockDetails(stock.getStockId());
		verify(stockRepository).deleteById("AMZN01");
	}
	@Test
	@DisplayName("Test for displaying all stock")
	public void showAllCompanyTest() {
		Company company = new Company("AMZN", "Amazon inc.", "E-Commerce");
		when(stockRepository.findAll())
				.thenReturn(Stream.of( new Stock("AMZN01", "Amazon", 50, "NSE", 1475, company))
					.collect(Collectors.toList()));
		assertEquals(1, stockService.viewAllStock().size());
	}

}