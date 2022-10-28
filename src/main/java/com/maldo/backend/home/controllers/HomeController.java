package com.maldo.backend.home.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Includes Login and Sign-In Controllers
 */

@RestController
public class HomeController
{
	@RequestMapping("/back/project/home")
	public String home() {
		return "home";
	}
}

