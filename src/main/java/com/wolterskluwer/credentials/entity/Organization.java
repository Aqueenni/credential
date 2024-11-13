package com.wolterskluwer.credentials.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

/**
 * @author aqueenni
 *
 *         6 Nov 2024
 */

@Entity
public class Organization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String vatNumber;

	@Column(nullable = false, unique = true)
	private String sapID;

	@OneToMany(mappedBy = "organization")
	private Set<Credential> credentials = new HashSet<>();

	@ManyToMany(mappedBy = "organizations")
	Set<User> users = new HashSet<>();

	public Organization(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVatNumber() {
		return vatNumber;
	}

	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}

	public String getSapID() {
		return sapID;
	}

	public void setSapID(String sapID) {
		this.sapID = sapID;
	}

	public Long getId() {
		return id;
	}

	public Set<Credential> getCredentials() {
		return credentials;
	}

	public void setCredentials(Set<Credential> credentials) {
		this.credentials = credentials;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Organization() {
		super();
		// TODO Auto-generated constructor stub
	}

}
