package com.wolterskluwer.credentials.util;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.wolterskluwer.credentials.dto.CustomUserDetail;
import com.wolterskluwer.credentials.entity.User;


/**
 * @author aqueenni
 *
 *         10 Nov 2024
 */

@Component
public class CommonUtils {

	public static User customUsertoUserEntity(CustomUserDetail customUserDetail) {
		User user = new User();
		user.setEmail(customUserDetail.getEmail());
		user.setFirstName(customUserDetail.getFirstName());
		user.setLastName(customUserDetail.getLastName());
		user.setSubjectId(customUserDetail.getSubjectId());
		user.setName(customUserDetail.getName());
		return user;
	}

	public static CustomUserDetail toCustomUserDetails(User user) {
		CustomUserDetail userDetails = new CustomUserDetail();

		userDetails.setSubjectId(Optional.ofNullable(user.getSubjectId()).orElse(""));
		userDetails.setName(Optional.ofNullable(user.getName()).orElse(""));
		userDetails.setFirstName(Optional.ofNullable(user.getFirstName()).orElse(""));
		userDetails.setLastName(Optional.ofNullable(user.getLastName()).orElse(""));
		userDetails.setEmail(Optional.ofNullable(user.getEmail()).orElse(""));

		userDetails.setOrganizations(Optional.ofNullable(user.getOrganizations()).orElse(new HashSet<>()));
		userDetails.setType(
				(user.getOrganizations() == null || user.getOrganizations().isEmpty()) ? UserType.NEW.getValue()
						: UserType.EXISTING.getValue());

		return userDetails;
	}

}
