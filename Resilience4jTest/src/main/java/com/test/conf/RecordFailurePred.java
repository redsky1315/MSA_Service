package com.test.conf;

import java.util.function.Predicate;

import org.springframework.web.client.HttpClientErrorException;

public class RecordFailurePred implements Predicate<Throwable> {

	@Override
	public boolean test(Throwable t) {
		if(t instanceof HttpClientErrorException) return false;
		return true;
	}

}
