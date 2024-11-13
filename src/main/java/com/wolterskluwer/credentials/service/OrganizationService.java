package com.wolterskluwer.credentials.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolterskluwer.credentials.entity.Organization;
import com.wolterskluwer.credentials.repository.OrganizationRepository;

/**
 * @author aqueenni
 *
 *         7 Nov 2024
 */

@Service
public class OrganizationService {

	@Autowired
	private OrganizationRepository organizationRepository;

	public List<Organization> getOrganizationList() {
		return organizationRepository.findAll();
	}

	public Set<Organization> getAllOrganization(Set<Long> id) {
		return organizationRepository.findOrganizationByIdIn(id);
	}

	public Organization getOrganizationById(Long id) {
		return organizationRepository.findOrganizationById(id);
	}

}
