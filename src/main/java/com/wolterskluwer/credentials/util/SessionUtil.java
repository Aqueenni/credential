package com.wolterskluwer.credentials.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wolterskluwer.credentials.dto.CustomUserDetail;

import jakarta.servlet.http.HttpSession;

/**
 * @author aqueenni
 *
 *         10 Nov 2024
 */

@Component
public class SessionUtil {

	@Autowired
	private HttpSession session;

	public void setUserDetails(CustomUserDetail userDetails) {
		session.setAttribute("userDetails", userDetails);
	}

	public CustomUserDetail getUserDetails() {
		return (CustomUserDetail) session.getAttribute("userDetails");
	}
}
