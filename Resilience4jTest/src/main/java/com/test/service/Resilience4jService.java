package com.test.service;

import java.util.Random;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Service;

import com.test.conf.Resilience4jCode;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Resilience4jService {

	private void randomlyRunLong() {
		Random rand = new Random();
		int randomNum = rand.nextInt(10)+1;
		log.info(String.valueOf(randomNum));
		sleep();
	}
	
	private void sleep() {
		try {
			Thread.sleep(5000);
			TimeoutException exception = new TimeoutException("고의로 생성");
			throw exception;
		}catch(InterruptedException|TimeoutException e) {
			e.printStackTrace();
		}
	}
	
	@CircuitBreaker(name=Resilience4jCode.RESSERVICE, fallbackMethod="getFallback")
	public String getResilienceTest() {
		randomlyRunLong();
		return "getResilienceTest!";
	}
	
	public String getFallback(Throwable t) {
		log.info("getFallback");
		log.info(String.valueOf(t.getClass()));
		log.info(String.valueOf(t.getMessage()));
		return "Fail! Fallback!";
	}
}
