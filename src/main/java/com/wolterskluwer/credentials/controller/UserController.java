package com.wolterskluwer.credentials.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wolterskluwer.credentials.dto.CreateUserRequest;
import com.wolterskluwer.credentials.service.UserService;

/**
 * @author aqueenni 7 Nov 2024
 */

@RestController
@RequestMapping("/api/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@PostMapping("/create")
	public ResponseEntity<?> createUser(@RequestBody CreateUserRequest requestBody) {

		Map<String, Object> response = userService.createUser(requestBody);
		logger.info("Response: {} ", response);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/json").body(response);
	}

}
