package com.test.service;

import org.springframework.stereotype.Service;

import com.test.conf.Resilience4jCode;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Resilience4jFailureService {
	private int counter = 0;
	
	@CircuitBreaker(name=Resilience4jCode.MYCIRCUITBREAKER, fallbackMethod="fallback")
	@Retry(name=Resilience4jCode.RETRYTEST, fallbackMethod="getFallback")
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
	public void getFallback(Exception ex) {
		log.error("Retry Fallback method triggered:"+ ex.getMessage());
	}
}
