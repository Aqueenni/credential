package com.wolterskluwer.credentials.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wolterskluwer.credentials.dto.ManageCredentialRequest;
import com.wolterskluwer.credentials.service.CredentialService;

/**
 * @author aqueenni
 * @date 7 Nov 2024
 */

@RestController
@RequestMapping("/api/credential")
public class CredentialController {

	private static final Logger logger = LoggerFactory.getLogger(CredentialController.class);
	
	@Autowired
	private CredentialService credentialService;

	@PostMapping("/create")
	public ResponseEntity<?> createCredential(@RequestBody ManageCredentialRequest requestBody) {
		try {
			Map<String, Object> response = credentialService.createCredential(requestBody);
			logger.info("Response: {} ", response);
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/json").body(response);			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error creating credential: " + e.getMessage());
		}
	}

	@PostMapping("/updateSecret/{id}")
	public ResponseEntity<?> regenerateClientSecret(@PathVariable Long id) {
		Object updatedCredential = credentialService.regenerateClientSecret(id);
		return ResponseEntity.ok(updatedCredential);
	}

	@PostMapping("/delete/{id}")
	public ResponseEntity<?> deleteCredential(@PathVariable Long id) {
		credentialService.deleteCredential(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
