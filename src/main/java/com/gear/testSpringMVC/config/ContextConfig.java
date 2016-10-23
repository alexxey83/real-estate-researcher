package com.gear.testSpringMVC.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gear.testSpringMVC.Servces.RentThemselvesService;

@Configuration
public class ContextConfig {
	
	@Bean
	public RentThemselvesService rentThemselvesSerives() {
		RentThemselvesService service = new RentThemselvesService();
		return service;
	}
}
