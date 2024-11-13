package com.wolterskluwer.credentials.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wolterskluwer.credentials.entity.Credential;

/**
 * @author aqueenni
 *
 *         7 Nov 2024
 */

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Long> {

	List<Credential> findByOrganizationId(Long organizationId);

	boolean existsByClientId(String clientId);

	Optional<Credential> findByClientId(String clientId);

	@Query("SELECT c.id,c.clientId,c.clientName,c.clientSecret,c.createdDate, c.expiryDate, c.organization.id"
			+ " FROM Credential c WHERE c.organization.id IN (:organizationIds) AND c.subjectId = :subjectId")
	Credential findBySubjectIdAndOrganizationId(@Param("subjectId") String subjectId,
			@Param("organizationId") Long organizationId);

	@Query("SELECT c.id,c.clientId,c.clientName,c.clientSecret,c.createdDate, c.expiryDate, c.organization.id"
			+ " FROM Credential c WHERE c.organization.id IN (:organizationIds) AND c.subjectId = :subjectId")
	List<Object[]> findCredentialsByOrganizationAndSubject(@Param("organizationIds") List<Long> organizationIds,
			@Param("subjectId") String subjectId);

}
