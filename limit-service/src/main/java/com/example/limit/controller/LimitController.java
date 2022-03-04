package com.example.limit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.limit.bean.Limit;
import com.example.limit.config.LimitConfigs;

@RestController
public class LimitController {

	@Autowired
	LimitConfigs config;
	
	@GetMapping("/get")
	public Limit get() {
		return new Limit(config.getMin(), config.getMax());
	}
}
