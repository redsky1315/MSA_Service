package com.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class CustomerController {
	
	@GetMapping("/customer")
	public String getCustomer() {
		log.info("Service request to get customer");
		return "Get Customer";
	}
}
