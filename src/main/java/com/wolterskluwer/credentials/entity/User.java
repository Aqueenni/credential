package com.wolterskluwer.credentials.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

/**
 * @author aqueenni
 *
 *         6 Nov 2024
 */

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false,unique=true)
	private String subjectId;

	@Column
	private String name;

	@Column(nullable = false)
	private String firstName;

	@Column
	private String lastName;

	@Column(nullable = false)
	private String email;

	@ManyToMany
	@JoinTable(name = "user_organizations", joinColumns = @JoinColumn(name = "user_id"), // Column for User's ID
			inverseJoinColumns = @JoinColumn(name = "organization_id")) // Column for Organization's ID
	private Set<Organization> organizations = new HashSet<>();

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String subjectId, String name, String firstName, String lastName, String email) {
		super();
		this.subjectId = subjectId;
		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
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

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Organization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(Set<Organization> organizations) {
		this.organizations = organizations;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

}
