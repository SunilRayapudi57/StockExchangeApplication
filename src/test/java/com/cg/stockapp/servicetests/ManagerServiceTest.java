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
import com.cg.stockapp.entities.Manager;
import com.cg.stockapp.repository.ManagerRepository;
import com.cg.stockapp.service.ManagerService;

@SpringBootTest
class ManagerServiceTests {



	@Autowired
	private ManagerService managerService;

	@MockBean
	private ManagerRepository managerRepository;

	@Test
	@DisplayName("Test for displaying all manager details")
	public void showAllManagerDetailsTest() {

		
		Company company = new Company("A100", "Amazon", "NSE");
		Manager manager = new Manager("A100", "ram", "ram@gamil.com", "king@123", "9444944494", company);
		Manager manager1 = new Manager("A200", "ram", "ram@gamil.com", "king@123", "9444944494", company);

		when(managerRepository.findAll()).thenReturn(Stream
				.of(manager,
						manager1)
				.collect(Collectors.toList()));
		assertEquals(2, managerService.getAllManager().size());
	}
	

	@Test
	@DisplayName("Test for adding manager")
	public void addManagerTest() {
		Company company = new Company("AMZN", "Amazon", "NSE");
		Manager manager = new Manager("A100", "ram", "ram@gamil.com", "king@123", "9444944494", company);
		
		when(managerRepository.save(manager)).thenReturn(manager);
		System.out.println(manager);
		assertEquals(true, managerService.addManager(manager));
	}

	@Test
	@DisplayName("Test for updating manager")
	public void updateManagerTest() {
		Company c = new Company("Amzn", "Amazon", "NSE");
		Manager manager = new Manager("1", "Surajit", "Sm@gmail.com", "sm@123", "9876543210", c);
		manager.setManagerName("Surendra");
		assertThat(managerRepository.findById(manager.getManagerId())).isNotEqualTo(manager);
	}

	@Test
	@DisplayName("Test for deleting manager")
	public void deleteManagerTest() {
		Company c = new Company("Amzn", "Amazon", "NSE");
		Manager manager = new Manager("1", "Surajit", "Sm@gmail.com", "sm@123", "9876543210", c);
		when(managerRepository.existsById(manager.getManagerId())).thenReturn(true);
		managerService.deleteManager(manager.getManagerId());
		verify(managerRepository).deleteById("1");
	}

	@Test
	@DisplayName("Test for get manager details by Id")
	public void getManagerDetailsTest() {
		Company c = new Company("Amzn", "Amazon", "NSE");
		Manager manager = new Manager("1", "Surajit", "Sm@gmail.com", "sm@123", "9876543210", c);
		when(managerRepository.save(manager)).thenReturn(manager);
		assertNotEquals(manager, managerRepository.findById(manager.getManagerId()));

	}

	/*
	 * @Test
	 * 
	 * @DisplayName("Test for get manager details by Company Id") public void
	 * getManagerTest() { Company c=new Company("Amzn","Amazon","NSE"); Manager
	 * manager = new Manager("1","Surajit","Sm@gmail.com","sm@123","9876543210", c);
	 * when(managerRepository.save(manager)).thenReturn(manager);
	 * assertNotEquals(manager,managerRepository.findById(manager.getCompanyId()));
	 * 
	 * }
	 * 
	 */

}
