package com.test.service;

import org.springframework.stereotype.Service;

import com.test.conf.Resilience4jCode;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Resilience4jFailureService {
	private int counter = 0;
	
	@CircuitBreaker(name=Resilience4jCode.MYCIRCUITBREAKER, fallbackMethod="fallback")
	public void performOperation() {
		counter++;
		if(counter % 3 !=0) {
			throw new RuntimeException("Simulated failure");
		}
		log.info("Operation succeeded");
	}
	
	public void fallback(Exception ex) {
		log.error("Fallback method triggered:"+ ex.getMessage());
	}
}
