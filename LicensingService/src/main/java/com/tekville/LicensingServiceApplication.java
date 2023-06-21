package com.tekville;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tekville.config.ServiceConfig;

@SpringBootApplication
@RestController
//@EnableDiscoveryClient //Spring Discovery Client
public class LicensingServiceApplication {
	
	private final ServiceConfig serviceConfig;
	//@Autowired
//	/private DiscoveryClient discoveryClient;
	
	/**
	 * LoadBalanced REST Template
	 * @return
	 */
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	LicensingServiceApplication(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }
	
	public static void main(String[] args) {
		SpringApplication.run(LicensingServiceApplication.class, args);
		
	}
	
	/*@GetMapping("/local")
	public ResponseEntity<LocalProp> loadServiceConfig() {
		LocalProp local = new LocalProp();
		local.setUrl(serviceConfig.getUrl());
		local.setUsername(serviceConfig.getUsername());
		local.setPassword(serviceConfig.getPassword());
		
		
		
		return ResponseEntity.ok(local);
	}*/
	
	/**
	 * Spring Discovery Client
	 * @return
	 */
	/*@GetMapping("/test")
	public String orgaCall() {
		List<ServiceInstance> instances = discoveryClient.getInstances("organization-service");
		String serviceUri = String.format("%s/%s", instances.get(0).getUri().toString(),"orga");
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> restExchange = restTemplate.exchange(serviceUri,
																	HttpMethod.GET,
																	null,
																	String.class,
																	"");
		return restExchange.getBody();
	}*/
	
	
	
	

}
