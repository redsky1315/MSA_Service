package com.tekville.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * LoadBalanced REST Template
 * @author user
 *
 */
@RestController
public class OrganizationRestTemplate {
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/test2")
	public String orgaCall2() {
		String serviceUri = String.format("%s/%s", "http://organization-service","orga");
		ResponseEntity<String> restExchange = restTemplate.exchange(serviceUri,
																	HttpMethod.GET,
																	null,
																	String.class,
																	"");
		return restExchange.getBody();
	}
}
