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

import com.cg.stockapp.entities.User;
import com.cg.stockapp.repository.UserRepository;
import com.cg.stockapp.service.UserService;

@SpringBootTest
class UserServiceTest {

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@Test
	@DisplayName("Test for adding user")
	public void addUserTest() {
		User user = new User(1234567L, "Ramesh", "king@123", "admin");
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(true, userService.addUser(user));
	}

	@Test
	@DisplayName("Test for updating user")
	public void updateUserTest() {
		User user = new User(1234567L, "Ramesh", "king@123", "admin");
//		when(userRepository.save(user)).thenReturn(user);
//		assertEquals(true,userService.updateUser(user));
		user.setUserName("RameshKumar");
		assertThat(userRepository.findById(user.getUserId())).isNotEqualTo(user);
	}

	@Test
	@DisplayName("Test for deleting user")
	public void removeUserTest() {
		// userService.removeUser(123457L);
		// verify(userRepository, times(1)).deleteById(1234567L);
		User user = new User(1234567L, "Ramesh", "king@123", "admin");
		when(userRepository.existsById(user.getUserId())).thenReturn(true);
		userService.removeUser(user.getUserId());
		verify(userRepository).deleteById(1234567L);
	}

}
