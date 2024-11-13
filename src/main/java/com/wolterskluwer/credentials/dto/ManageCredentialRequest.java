package com.wolterskluwer.credentials.dto;

public class ManageCredentialRequest {
	private Long selectedOrgIds;
	private CustomUserDetail userDetails;

	public Long getSelectedOrgIds() {
		return selectedOrgIds;
	}

	public void setSelectedOrgIds(Long selectedOrgIds) {
		this.selectedOrgIds = selectedOrgIds;
	}

	public CustomUserDetail getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(CustomUserDetail userDetails) {
		this.userDetails = userDetails;
	}

}