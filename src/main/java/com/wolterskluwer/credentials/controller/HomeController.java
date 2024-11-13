package com.wolterskluwer.credentials.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.wolterskluwer.credentials.dto.CustomUserDetail;
import com.wolterskluwer.credentials.service.HomeService;

/**
 * @author aqueenni
 *
 *         6 Nov 2024
 */

@Controller
@RequestMapping("/api")
public class HomeController {

	@Autowired
	private HomeService homeService;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@GetMapping(value = "/home")
	public String displayLoginMessage(Model model, @SessionAttribute("userDetails") CustomUserDetail userDetails) {
		try {
			homeService.displayHomePageMessage(model, userDetails);
			return "homePage";
		} catch (Exception ex) {
			model.addAttribute("message", "Something went wrong. Please try again later.");
			logger.error("Error occurred in displayLoginMessage: ", ex);
			return "errorPage";
		}
	}
}
