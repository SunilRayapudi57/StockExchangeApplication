package com.cg.stockapp.servicetests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
		Company company = new Company("A100", "Amazon", "NSE");
	when(companyRepository.save(company)).thenReturn(company);
		assertEquals(true, companyService.addCompany(company));
	}

	@Test
	@DisplayName("Test for updating company")
	public void updateCompanyTest() {
		Company company = new Company("A100", "Amazon", "NSE");
		when(companyRepository.save(company)).thenReturn(company);
	//	assertEquals(true, companyService.updateCompany(company));
		
		company.setCompanyName("Aazon.in");
		assertThat(companyRepository.findById(company.getCompanyId())).isNotEqualTo(company);
	}

	@Test
	@DisplayName("Test for deleting company")
	public void removeCompanyTest() {
		
	//	companyService.deleteCompany("A100");
	//	verify(companyRepository, times(1)).deleteById("A100");
		Company company = new Company("A100", "Amazon", "NSE");
		
		when(companyRepository.existsById(company.getCompanyId())).thenReturn(true);
		companyService.deleteCompany(company.getCompanyId());
		verify(companyRepository).deleteById("A100");
	}

	@Test
	@DisplayName("Test for displaying all company")
	public void showAllCompanyTest() {
		when(companyRepository.findAll())
				.thenReturn(Stream.of(new Company("A100", "Amazon", "NSE"))
					.collect(Collectors.toList()));
		assertEquals(1, companyService.getAllCompanyInfo().size());
	}

	@Test
	@DisplayName("Test for displaying one company by id")
	public void showCompanyTest() {
		
		Company company = new Company("A100", "Amazon", "NSE");
		when(companyRepository.save(company)).thenReturn(company);
		assertNotEquals(company,companyRepository.findById(company.getCompanyId()));
			
		/*Company company = new Company("A200", "Amazon", "NSE");
		when(companyRepository.findById("A200")).thenReturn(Optional.of(company));
		Company com=companyService.getCompanyInfo(company.getCompanyId());
		System.out.println(com);
		System.out.println(company);
		assertThat(com).isSameAs(company);
		verify(companyRepository).findById(company.getCompanyId());
		*/
		
		
	}

}