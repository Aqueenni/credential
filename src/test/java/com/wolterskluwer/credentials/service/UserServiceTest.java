package com.wolterskluwer.credentials.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wolterskluwer.credentials.dto.CreateUserRequest;
import com.wolterskluwer.credentials.dto.CustomUserDetail;
import com.wolterskluwer.credentials.entity.Organization;
import com.wolterskluwer.credentials.entity.User;
import com.wolterskluwer.credentials.repository.OrganizationRepository;
import com.wolterskluwer.credentials.repository.UserRepository;
import com.wolterskluwer.credentials.util.CommonUtils;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

	@InjectMocks
	private UserService userService;

	@Mock
	private UserRepository userRepository;

	@Mock
	private OrganizationRepository organizationRepository;

	@Mock
	private OrganizationService organizationService;

	@Mock
	private CommonUtils commonUtils;

	private CreateUserRequest createUserRequest;
	private CustomUserDetail customUserDetail;
	private Set<Long> orgIds;
	private User user;
	private Organization org1, org2;

	@BeforeEach
	public void setUp() {
		orgIds = new HashSet<>(Arrays.asList(1L, 2L));

		org1 = new Organization(1L, "Org 1");
		org2 = new Organization(2L, "Org 2");
		Set<Organization> organizations = new HashSet<>(Arrays.asList(org1, org2));

		customUserDetail = new CustomUserDetail();
		customUserDetail.setSubjectId("12345");
		customUserDetail.setEmail("test@example.com");
		customUserDetail.setFirstName("John");
		customUserDetail.setLastName("Doe");

		createUserRequest = new CreateUserRequest();
		createUserRequest.setUserDetails(customUserDetail);
		createUserRequest.setSelectedOrgIds(orgIds);

		user = new User();
		user.setSubjectId(customUserDetail.getSubjectId());
		user.setEmail(customUserDetail.getEmail());
		user.setFirstName(customUserDetail.getFirstName());
		user.setLastName(customUserDetail.getLastName());
		user.setOrganizations(organizations);
	}

	@Test
	public void testCreateUserSuccess() {
		Set<Organization> userOrgSet = new HashSet<>(Arrays.asList(org1, org2));
		when(organizationService.getAllOrganization(anySet())).thenReturn(userOrgSet);
		when(userService.findExistingUser(anyString())).thenReturn(Optional.empty());
		when(userRepository.save(any(User.class))).thenReturn(user);
		Map<String, Object> result = userService.createUser(createUserRequest);
		assertNotNull(result);
		assertEquals("User Account Created!!", result.get("message"));
		assertEquals(userOrgSet, result.get("userOrgSet"));
		assertEquals(customUserDetail.getFirstName(), result.get("name"));
	}

	@Test
	public void testCreateUserAlreadyExists() {
		when(userService.findExistingUser(anyString())).thenReturn(Optional.of(user));

		assertThrows(RuntimeException.class, () -> {
	        userService.createUser(createUserRequest);
	    });
	}

}
