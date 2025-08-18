package com.example.orchestrator.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.orchestrator.IServices.IOrchestratorService;
import com.example.orchestrator.models.Name;

@Service
public class OrchestrationService implements IOrchestratorService{
	
	private static final Logger logger = LoggerFactory.getLogger(OrchestrationService.class);

	
	@Value("${services.full-name.base-url}")
	private String fullNameServiceUrl;
	
	@Value("${services.greeting.base-url}")
	private String greetingServiceUrl;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public String retrieveFullName(Name name, String traceId) {
		
		logger.info("Trace id : {} | Method : {}", traceId, "retrieveFullName()"); 

		String response = "";
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-Trace-Id", traceId);
		HttpEntity<Name> entity = new HttpEntity<Name>(name, headers);
		
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(
					fullNameServiceUrl + "/api/v1/full-name", HttpMethod.POST, entity, String.class);
			response = responseEntity.getBody();
		}catch(RestClientException e) {
			logger.error("Full Name service is DOWN, traceId = {}", traceId, e);
//		    return "Full Name service unavailable";
		}
		
		
		return response;
	}
	
	

	@Override
	public String retrieveGreeting(String traceId) {
		
		logger.info("Trace id : {} | Method : {}", traceId, "retrieveGreeting()"); 
		
		String response = "";
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-Trace-Id", traceId);

		try {
			ResponseEntity<String> greetingResponse = restTemplate.exchange(
					greetingServiceUrl + "/api/v1/get", HttpMethod.GET, new HttpEntity<>(headers), String.class);
			response = greetingResponse.getBody();
		}catch(RestClientException e) {
			logger.error("Greeting service is DOWN, traceId = {}", traceId, e);
//			return "Greeting service unavailable";
		}
		

		
		return response;
	}

}
