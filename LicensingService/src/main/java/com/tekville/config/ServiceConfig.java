package com.tekville.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@RefreshScope
@Getter
@Setter
@ToString
public class ServiceConfig {
	/*@Value("${spring.datasource.uri}")
	private String url="";
	@Value("${spring.datasource.username}")
	private String username="";
	@Value("${spring.datasource.password}")
	private String password="";
	*/
	
	
}
