package com.example.orchestrator.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TestController {
	
	@GetMapping("/test")
	public String test() {
		return "Testing Successful";
	}
	
	@GetMapping("/test2")
	public List<String> test2(){
		return List.of("Testing 1", "Testing 2");
	}

}
