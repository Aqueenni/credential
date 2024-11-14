package com.wolterskluwer.credentials.config;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.wolterskluwer.credentials.dto.CustomUserDetail;
import com.wolterskluwer.credentials.entity.Organization;
import com.wolterskluwer.credentials.entity.User;
import com.wolterskluwer.credentials.service.UserService;
import com.wolterskluwer.credentials.util.CommonUtils;
import com.wolterskluwer.credentials.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

	@Autowired
	private UserService userService;

	@Autowired
	private SessionUtil sessionUtil;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
		User user = findUserDetails(oidcUser);
		CustomUserDetail userDetails = CommonUtils.toCustomUserDetails(user);
		sessionUtil.setUserDetails(userDetails);
		logger.info("Authentication successful for user: {}", user.getFirstName());
		setDefaultTargetUrl("/home");

		super.onAuthenticationSuccess(request, response, authentication);
	}

	private User findUserDetails(OidcUser oidcUser) {
		String subjectId = oidcUser.getSubject();
		String firstName = oidcUser.getGivenName();
		String lastName = oidcUser.getFamilyName();
		String email = oidcUser.getEmail();
		String name = oidcUser.getFullName();
		Optional<User> existingUser = userService.findExistingUser(subjectId);
		User currentUser = existingUser.orElseGet(() -> {
			User newUser = new User(subjectId, name, firstName, lastName, email);
			logger.info("New user found: {}", newUser.getFirstName());
			return newUser;
		});

		if (existingUser.isPresent()) {
			currentUser = existingUser.get();
			Set<Organization> orgSet = userService.getOrganizationForExistingUser(currentUser.getId());
			currentUser.setOrganizations(orgSet);
			logger.info("Existing user found: {}", existingUser.get().getFirstName());
		}
		return currentUser;
	}

}
