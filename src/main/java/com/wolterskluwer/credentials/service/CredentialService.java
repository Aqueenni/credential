package com.wolterskluwer.credentials.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolterskluwer.credentials.dto.CredentialDTO;
import com.wolterskluwer.credentials.dto.CustomUserDetail;
import com.wolterskluwer.credentials.dto.ManageCredentialRequest;
import com.wolterskluwer.credentials.entity.Credential;
import com.wolterskluwer.credentials.entity.Organization;
import com.wolterskluwer.credentials.repository.CredentialRepository;

/**
 * @author aqueenni
 *
 *         7 Nov 2024
 */

@Service
public class CredentialService {

	@Autowired
	private CredentialRepository credentialRepository;

	@Autowired
	private OrganizationService organizationService;

	private static final int EXPIRY_DAYS = 365;
	private static final SecureRandom secureRandom = new SecureRandom();

	public Map<String, Object> createCredential(ManageCredentialRequest requestBody) {
		CustomUserDetail userDetails = requestBody.getUserDetails();
		String subjectId = userDetails.getSubjectId();
		Long orgId = requestBody.getSelectedOrgIds();
		Credential existingCredential = credentialRepository.findBySubjectIdAndOrganizationId(subjectId, orgId);

		if (existingCredential != null) {
			Organization userOrg = organizationService.getOrganizationById(orgId);
			Credential credential = createCredential(userOrg, subjectId);

			return Map.of("message", "Credentials Created!!", "orgId", orgId, "name", credential.getClientName());
		} else {
			return Map.of("message", "Credentials for this organization already exist.", "orgId", orgId);
		}
	}

	public Credential createCredential(Organization organization, String subjectId) {
		Credential credential = new Credential();
		credential.setClientId(generateClientId());
		credential.setClientSecret(generateClientSecret());
		credential.setOrganization(organization);
		credential.setExpiryDate(LocalDate.now().plusDays(EXPIRY_DAYS));
		credential.setCreatedDate(LocalDate.now());
		credential.setExpiryDate(LocalDate.now().plusYears(1));
		credential.setClientName(organization.getName());
		credential.setSubjectId(subjectId);
		return credentialRepository.save(credential);
	}

	private String generateClientId() {
		return UUID.randomUUID().toString();
	}

	private String generateClientSecret() {
		return new BigInteger(130, secureRandom).toString(32);
	}

	public Credential regenerateClientSecret(Long credentialId) {
		Credential credential = credentialRepository.findById(credentialId)
				.orElseThrow(() -> new IllegalArgumentException("Credential not found"));
		credential.setClientSecret(generateClientSecret());
		credential.setExpiryDate(LocalDate.now().plusDays(EXPIRY_DAYS));
		return credentialRepository.save(credential);
	}

	public List<Credential> findCredentialsByOrganization(Long organizationId) {
		return credentialRepository.findByOrganizationId(organizationId);
	}

	public void deleteCredential(Long credentialId) {
		credentialRepository.deleteById(credentialId);
	}

	public Credential findCredentialsBySubjectAndOrganization(String subjectId, Long orgId) {
		return credentialRepository.findBySubjectIdAndOrganizationId(subjectId, orgId);
	}

	public List<CredentialDTO> getCredentialsForOrganizationsAndSubject(List<Long> organizationIds, String subjectId) {
		List<Object[]> results = credentialRepository.findCredentialsByOrganizationAndSubject(organizationIds,
				subjectId);
		List<CredentialDTO> credentialDTOs = new ArrayList<>();
		for (Object[] result : results) {
			CredentialDTO dto = new CredentialDTO(result);
			credentialDTOs.add(dto);
		}
		return credentialDTOs;
	}

}
