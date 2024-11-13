package com.wolterskluwer.credentials.dto;

import java.util.Date;

public class CredentialDTO {

	private Long id;
	private String clientId;
	private String clientName;
	private String clientSecret;
	private Date createdDate;
	private Date expiryDate;
	private Long organizationId;
	private String subjectId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public CredentialDTO(Object[] result) {
		this.id = (Long) result[0];
		this.clientId = (String) result[1];
		this.clientName = (String) result[2];
		this.clientSecret = (String) result[3];
		this.createdDate = (Date) result[4];
		this.expiryDate = (Date) result[5];
		this.organizationId = (Long) result[6];
	}

	public CredentialDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
