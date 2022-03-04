package com.example.currency;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	@Autowired
	private Environment env;
	
	@Autowired
	private CurrencyExchangeRepo repo;
	

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange exchangeValue(@PathVariable String from, @PathVariable String to) {
		String port = env.getProperty("local.server.port");
		CurrencyExchange currencyExchange = repo.findByFromAndTo(from, to);
		if(currencyExchange == null) {
			throw new RuntimeException("Cannot get values for "+from+" to "+to);
		}
		currencyExchange.setEnvironment(port);
		return currencyExchange;
	}
}
