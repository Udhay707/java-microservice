package com.example.currency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController

public class CircuitController {

	private Logger logger = LoggerFactory.getLogger(CircuitController.class);

	@GetMapping("/sample-api")
	//@Retry(name="sample-api", fallbackMethod = "someResponse")
	@RateLimiter(name="sample-api")
	public String getSampleAPI() {
		
//		logger.info("Sample API call received");
//		
//		ResponseEntity<String> forEntity = new RestTemplate()
//				.getForEntity("http://localhost:8080/get-api", String.class);
		
		
		//return forEntity.getBody();
		
		return "Rate Limited";
	}
	
	public String someResponse(Exception ex){
		return "unable to connect";
	}
}
