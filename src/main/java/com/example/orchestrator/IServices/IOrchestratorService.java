package com.example.orchestrator.IServices;

import com.example.orchestrator.models.Name;

public interface IOrchestratorService {
	
	public String retrieveFullName(Name name, String traceId);
	
	public String retrieveGreeting(String traceId);

}
