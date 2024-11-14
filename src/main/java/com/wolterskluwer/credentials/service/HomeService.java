package com.wolterskluwer.credentials.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.wolterskluwer.credentials.dto.CustomUserDetail;

/**
 * @author aqueenni
 *
 *         7 Nov 2024
 */

@Service
public class HomeService {

	private static final Logger logger = LoggerFactory.getLogger(HomeService.class);

	public void displayHomePageMessage(Model model, CustomUserDetail userDetails) {
		try {
			if (userDetails != null && !userDetails.getSubjectId().isEmpty()) {
				//model.addAttribute("name", userDetails.getFirstName());
				model.addAttribute("user", userDetails);
				model.addAttribute("message", "Login is successful !!!!! ");
			} else {
				model.addAttribute("message", "Something went wrong. Please try again later");
			}
		} catch (Exception ex) {
			model.addAttribute("message", "Something went wrong. Please try again later.");
			logger.error("Error occurred while preparing the home page message: ", ex);
		}
	}
	
}
