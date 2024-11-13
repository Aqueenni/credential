package com.wolterskluwer.credentials.dto;

import java.util.HashSet;
import java.util.Set;

import com.wolterskluwer.credentials.entity.Organization;

public class CustomUserDetail {
	private String subjectId;
	private String email;
	private String firstName;
	private String lastName;
	private String name;
	private String type;

	Set<Organization> organizations = new HashSet<>();

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Organization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(Set<Organization> organizations) {
		this.organizations = organizations;
	}

	public CustomUserDetail(String subjectId, String email, String firstName, String lastName, String name) {
		super();
		this.subjectId = subjectId;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.name = name;
	}

	public CustomUserDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

}
