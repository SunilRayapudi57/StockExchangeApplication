package com.cg.stockapp.servicetests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.stockapp.entities.Admin;
import com.cg.stockapp.repository.AdminRepository;
import com.cg.stockapp.service.AdminService;

@SpringBootTest
public class AdminServiceTest {
	
	@Autowired
	private AdminService adminservice;
	@MockBean
	private AdminRepository adminrepository;

	@Test
	@DisplayName("Test for adding admin")
	public void addAdminTest() {
		Admin admin = new Admin("12BCE", "Lucifer", "lucifer@gmail.com", "Lucifer@987");
		when(adminrepository.save(admin)).thenReturn(admin);
		assertEquals(true, adminservice.addAdmin(admin));
	}

	@Test
	@DisplayName("Test for updating  admin")
	public void UpdatedAdminTest() {
		Admin admin = new Admin("12BCE", "Lucifer", "lucifer@gmail.com", "Lucifer@987");
		admin.setAdminName("RameshKumar");
		assertThat(adminrepository.findById(admin.getAdminId())).isNotEqualTo(admin);
	}

	@Test
	@DisplayName("Test for removing admin")
	public void addRemoveTest() {
		Admin admin = new Admin("12BCE", "Lucifer", "lucifer@gmail.com", "Lucifer@987");
		when(adminrepository.existsById(admin.getAdminId())).thenReturn(true);
		adminservice.removeAdmin(admin.getAdminId());
		verify(adminrepository).deleteById("12BCE");
	}

}
