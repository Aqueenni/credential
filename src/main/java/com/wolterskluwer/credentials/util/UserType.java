package com.wolterskluwer.credentials.util;

/**
 * @author aqueenni
 *
 *         10 Nov 2024
 */

public enum UserType {
	NEW("new"), EXISTING("existing");

	private final String value;

	UserType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}