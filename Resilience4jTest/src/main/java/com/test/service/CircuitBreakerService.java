package com.test.service;

import org.springframework.stereotype.Service;

import com.test.conf.Resilience4jCode;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CircuitBreakerService {
	
	@CircuitBreaker(name = Resilience4jCode.CIRCUIT_TEST_70000, fallbackMethod = "getCircuitBreakerFallback")
    public String getCircuitBreaker() {
	  log.info("getCircuitBreaker");
	  runtimeException();
      return "hello world!";
    }

    private void runtimeException() {
    	log.info("runtimeException");
    	throw new RuntimeException("failed");
    }
    private String getCircuitBreakerFallback(Throwable t) {
    	log.info("getCircuitBreakerFallback");
    	return "getCircuitBreakerFallback! exception type: " + t.getClass() + "exception, message: " + t.getMessage();
    }
}
