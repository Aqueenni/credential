package com.wolterskluwer.credentials.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wolterskluwer.credentials.entity.Organization;

/**
 * @author aqueenni
 *
 *         7 Nov 2024
 */
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

	Set<Organization> findOrganizationByIdIn(Set<Long> id);

	Organization findOrganizationById(Long id);

}
