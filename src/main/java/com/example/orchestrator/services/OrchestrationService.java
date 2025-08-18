package com.example.orchestrator.services;

import org.springframework.stereotype.Service;

import com.example.orchestrator.IServices.IOrchestratorService;
import com.example.orchestrator.models.Name;

@Service
public class OrchestrationService implements IOrchestratorService{
	
	private String greetingServiceUrl;
	
	
	private String fullNameServiceUrl;

	@Override
	public String retrieveFullName(Name name) {
		
		
		return null;
	}

	@Override
	public String retrieveGreeting() {

		
		return null;
	}

}
