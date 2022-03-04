package com.api.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class LoggerClass implements GlobalFilter{

	private Logger log = LoggerFactory.getLogger(LoggerClass.class);
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, 
								GatewayFilterChain chain) {
		
		
		log.info("Path of request -> {}",exchange.getRequest().getPath());
		return chain.filter(exchange);
	}

}
