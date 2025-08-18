package com.example.orchestrator.controllers;

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
	
	@Autowired
	private IOrchestratorService orchestratorService;
	
	@GetMapping("/health")
	public String getHealth() {
		return "Up";
	}
	
	
	@PostMapping("/greeting")
	public ResponseEntity<String> getResponse(@RequestBody(required = true) Name name) {
		String greeting = orchestratorService.retrieveGreeting();
		String fullName = orchestratorService.retrieveFullName(name);
		String response = greeting + fullName;
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
