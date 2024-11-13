package com.wolterskluwer.credentials.service;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolterskluwer.credentials.dto.CreateUserRequest;
import com.wolterskluwer.credentials.dto.CustomUserDetail;
import com.wolterskluwer.credentials.entity.Organization;
import com.wolterskluwer.credentials.entity.User;
import com.wolterskluwer.credentials.repository.OrganizationRepository;
import com.wolterskluwer.credentials.repository.UserRepository;
import com.wolterskluwer.credentials.util.CommonUtils;

/**
 * @author aqueenni
 *
 *         7 Nov 2024
 */

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrganizationRepository organizationRepository;

	@Autowired
	private OrganizationService organizationService;

	public User saveUser(User user, Set<Long> selectedOrgIds) {
		String subjectId = user.getSubjectId();
		Set<Organization> organizations = new HashSet<>(organizationRepository.findAllById(selectedOrgIds));
		user.setOrganizations(organizations);
		user = userRepository.save(user);
		logger.info("Created new user with Subject ID: {}", subjectId);
		return user;
	}

	public Optional<User> findExistingUser(String subjectId) {
		return userRepository.findUserBySubjectId(subjectId);
	}

	public Map<String, Object> createUser(CreateUserRequest requestBody) {

		Set<Organization> userOrgSet = null;
		CustomUserDetail userDetails = requestBody.getUserDetails();
		try {
			if (userDetails == null || requestBody.getSelectedOrgIds() == null) {
				throw new IllegalArgumentException("Invalid input. Please provide all required fields.");
			}
			userOrgSet = organizationService.getAllOrganization(requestBody.getSelectedOrgIds());
			User user = CommonUtils.customUsertoUserEntity(userDetails);

			Optional<User> existingUser = findExistingUser(userDetails.getSubjectId());
			if (existingUser.isPresent()) {
				userOrgSet = userDetails.getOrganizations();
				throw new IllegalStateException("User already exists. You cannot add an organization now.");
			}
			saveUser(user, requestBody.getSelectedOrgIds());
		} catch (RuntimeException ex) {
			throw new RuntimeException("Error while saving user: " + ex.getMessage());
		}

		return Map.of("message", "User Account Created!!", "userOrgSet", userOrgSet, "name",
				userDetails.getFirstName());
	}

	public Set<Organization> getOrganizationForExistingUser(Long id) {
		return userRepository.findOrganizationsByUserId(id);		
	}

}
