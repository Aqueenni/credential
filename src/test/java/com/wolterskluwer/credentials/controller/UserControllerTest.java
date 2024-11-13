package com.wolterskluwer.credentials.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolterskluwer.credentials.dto.CreateUserRequest;
import com.wolterskluwer.credentials.dto.CustomUserDetail;
import com.wolterskluwer.credentials.service.UserService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserControllerTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	public void testCreateUserSuccess() throws Exception {
		CreateUserRequest request = createTestCreateUserRequest();
		mockMvc.perform(post("/api/user/create").contentType("application/json").content(asJsonString(request)))
				.andExpect(status().isOk());
	}

	private String asJsonString(final Object obj) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private CreateUserRequest createTestCreateUserRequest() {
		CreateUserRequest request = new CreateUserRequest();
		request.setUserDetails(new CustomUserDetail("12345", "test@example.com", "John", "Doe", "John Doe"));
		request.setSelectedOrgIds(Set.of(1L, 2L));
		return request;
	}
}
