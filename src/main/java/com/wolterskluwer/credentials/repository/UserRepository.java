package com.wolterskluwer.credentials.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wolterskluwer.credentials.dto.UserDTO;
import com.wolterskluwer.credentials.entity.Organization;
import com.wolterskluwer.credentials.entity.User;

/**
 * @author aqueenni
 *
 *         7 Nov 2024
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User save(UserDTO user);

	User findUserByEmail(String email);

	Optional<User> findUserBySubjectId(String subject);

	public void save(Optional<User> user);

	Set<Organization> findOrganizationsById(Long userId); // native query find out

	@Query("SELECT o FROM Organization o JOIN o.users u WHERE u.id = :userId")
	Set<Organization> findOrganizationsByUserId(@Param("userId") Long userId);

}
