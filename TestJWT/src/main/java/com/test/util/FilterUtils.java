package com.test.util;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
@Component
public class FilterUtils {
	public static final String AUTH_TOKEN     = "Authorization";
	public String getAuthToken(HttpHeaders requestHeaders){
		if (requestHeaders.get(AUTH_TOKEN) !=null) {
			List<String> header = requestHeaders.get(AUTH_TOKEN);
			return header.stream().findFirst().get();
		}
		else{
			return null;
		}
	}
}
