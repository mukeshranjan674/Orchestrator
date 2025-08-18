package com.example.orchestrator.controllers;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.orchestrator.IServices.IOrchestratorService;
import com.example.orchestrator.models.Name;

@RestController
@RequestMapping("api/v1")
public class OrchestratorController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrchestratorController.class);
	
	@Autowired
	private IOrchestratorService orchestratorService;
	
	public record Response(String response) {};
	
	@GetMapping("/health")
	public ResponseEntity<String> getHealth() {
		String traceId = UUID.randomUUID().toString();
		logger.info("Trace id : {} | API : {} | details : {}", traceId, "GET /health ", "Health status"); 
		return ResponseEntity.ok("Up");
	}
	
	
	@PostMapping("/greeting")
	public ResponseEntity<Response> getResponse(@RequestBody(required = true) Name name) {
		String traceId = UUID.randomUUID().toString();
		logger.info("Trace id : {} | API : {} | payload/details : {}", traceId, "POST /greeting", name.toString()); 
		
		String greeting = orchestratorService.retrieveGreeting(traceId);
		String fullName = orchestratorService.retrieveFullName(name, traceId);
		String response = String.join(" ", greeting, fullName);
		
		return new ResponseEntity<>(new Response(response), HttpStatus.OK);
	}

}
