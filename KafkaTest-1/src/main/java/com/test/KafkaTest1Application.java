package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@SpringBootApplication
public class KafkaTest1Application {
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaTest1Application.class, args);
	}
	
	@PostMapping("/publish")
	public String publish(@RequestBody String stat) {
		/*int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        log.info(generatedString);*/
		log.info(stat);
		this.kafkaTemplate.send("peter-overview01", stat);

        return "success";
	}
	
	@GetMapping("/consume")
	@KafkaListener(topics="peter-overview01", groupId="my_group")
	public String consume(String message) {
		log.info("Consume => "+message);
		return message;
	}
	
	

}
