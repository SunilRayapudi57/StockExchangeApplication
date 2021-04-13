package com.cg.stockapp.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.stockapp.entities.Company;
import com.cg.stockapp.repository.CompanyRepository;
import com.cg.stockapp.service.CompanyService;

@SpringBootTest
public class CompanyServiceTest {

	@Autowired
	private CompanyService companyService;

	@MockBean
	private CompanyRepository companyRepository;

	@Test
	@DisplayName("Test for adding company")
	public void addCompanyTest() {
		Company company = new Company("AMZN", "Amazon", "NSE");
		when(companyRepository.save(company)).thenReturn(company);
		assertEquals(company, companyService.addCompany(company));
	}

	@Test
	@DisplayName("Test for updating company")
	public void updateCompanyTest() {
		Company company = new Company("AMZN", "Amazon inc.", "NSE");
		when(companyRepository.save(company)).thenReturn(company);
		assertEquals(company, companyService.updateCompany(company));
	}

	@Test
	@DisplayName("Test for deleting company")
	public void removeCompanyTest() {
		companyService.deleteCompany("AMZN");
		verify(companyRepository, times(1)).deleteById("AMZN");
	}

	@Test
	@DisplayName("Test for displaying all company")
	public void showAllCompanyTest() {
		when(companyRepository.findAll())
				.thenReturn(Stream.of(new Company("AMZN", "Amazon", "NSE"))
						.collect(Collectors.toList()));
		assertEquals(1, companyService.getAllCompanyInfo().size());
	}

	@Test
	@DisplayName("Test for displaying one company by id")
	public void showCompanyTest() {
		Company company = new Company("AMZN", "Amazon", "NSE");
		when(companyRepository.findById("AMZN")).thenReturn(Optional.of(company));
		assertEquals(company, companyService.getCompanyInfo("AMZN"));
	}
}
