package com.test.controller;

import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.service.CircuitBreakerService;
import com.test.service.Resilience4jFailureService;
import com.test.service.Resilience4jService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CircuitBreakerController {
	@Autowired
	private final CircuitBreakerService circuitBreakerService;
	@Autowired
	private final Resilience4jService resilienceService;
	@Autowired
	private final Resilience4jFailureService failureService;
	
	@GetMapping("/hello")
    public String hello() {
      return circuitBreakerService.getCircuitBreaker();
    }
	
	@GetMapping("/resilientTest")
	public String remoteCall3() throws TimeoutException{
		log.info("remoteCall3");
		String result = resilienceService.getResilienceTest();
		return result;
	}
	
	@GetMapping("/resilientTest2")
	public ResponseEntity<String> remoteCall4() throws TimeoutException{
		log.info("remoteCall4");
		failureService.performOperation();
		return ResponseEntity.badRequest().body("remoteCall4");
	}
}
