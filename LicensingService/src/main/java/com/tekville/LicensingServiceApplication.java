package com.tekville;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tekville.config.ServiceConfig;
import com.tekville.model.LocalProp;

@SpringBootApplication
@RestController
public class LicensingServiceApplication {
	
	private final ServiceConfig serviceConfig;
	
    LicensingServiceApplication(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }
	
	public static void main(String[] args) {
		SpringApplication.run(LicensingServiceApplication.class, args);
		
	}
	
	@GetMapping("/local")
	public ResponseEntity<LocalProp> loadServiceConfig() {
		LocalProp local = new LocalProp();
		local.setUrl(serviceConfig.getUrl());
		local.setUsername(serviceConfig.getUsername());
		local.setPassword(serviceConfig.getPassword());
		return ResponseEntity.ok(local);
	}
	
	

}
