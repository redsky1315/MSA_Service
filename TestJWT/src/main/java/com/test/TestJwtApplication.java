package com.test;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.test.util.FilterUtils;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@RestController
@Slf4j
public class TestJwtApplication {

	@Autowired
	FilterUtils filterUtils;
	
	public static void main(String[] args) {
		SpringApplication.run(TestJwtApplication.class, args);
	}
	
	@GetMapping("/test")
	public void getChk(@RequestHeader HttpHeaders requestHeaders) {
		System.out.println("preferred_username:"+getUsername(requestHeaders));
	}
	
	public String getUsername(HttpHeaders requestHeaders){
		String username = "";
		if (filterUtils.getAuthToken(requestHeaders)!=null){
			String authToken = filterUtils.getAuthToken(requestHeaders).replace("Bearer ","");
	        JSONObject jsonObj = decodeJWT(authToken);
	        try {
	        	username = jsonObj.getString("azp");
	        }catch(Exception e) {
	        	log.debug(e.getMessage());
	        }
		}
		return username;
	}
	
	public JSONObject decodeJWT(String JWTToken) {
		String[] split_string = JWTToken.split("\\.");
		String base64EncodedBody = split_string[1];
		Base64 base64Url = new Base64(true);
		String body = new String(base64Url.decode(base64EncodedBody));
		JSONObject jsonObj = new JSONObject(body);
		return jsonObj;
	}

}
