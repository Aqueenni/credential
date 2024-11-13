package com.wolterskluwer.credentials.dto;

import java.util.Set;

public class CreateUserRequest {
	private Set<Long> selectedOrgIds;
	private CustomUserDetail userDetails;

	public Set<Long> getSelectedOrgIds() {
		return selectedOrgIds;
	}

	public void setSelectedOrgIds(Set<Long> selectedOrgIds) {
		this.selectedOrgIds = selectedOrgIds;
	}

	public CustomUserDetail getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(CustomUserDetail userDetails) {
		this.userDetails = userDetails;
	}

}