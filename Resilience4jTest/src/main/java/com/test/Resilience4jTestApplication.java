package com.test;

import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.service.CircuitBreakerService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@SpringBootApplication
@RestController
public class Resilience4jTestApplication {
	@Autowired
	CircuitBreakerService cService;
	
	public static void main(String[] args) {
		SpringApplication.run(Resilience4jTestApplication.class, args);
	}
	
	@GetMapping("/circuitTest")
	public String CircuitTest() {
		log.info("circuitTest!");
		return cService.getCircuitBreaker();
	}
	
	
	
}
