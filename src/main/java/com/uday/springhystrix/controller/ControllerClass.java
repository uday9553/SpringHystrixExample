package com.uday.springhystrix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ControllerClass {

	@Autowired
    RestTemplate restTemplate;

	@GetMapping("/hello")
	public String getHello() {
		return "hello from service";
	}
	
	
	
	@GetMapping("/hello1")
	@HystrixCommand(fallbackMethod = "fallbackHello")
	public String getHello1(){
		return restTemplate.getForObject("http://localhost:8080/hel", String.class);		
	}
	
	public String fallbackHello() {
		return "hello from  fallbackHello";
	}
}
