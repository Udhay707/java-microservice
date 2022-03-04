package com.currency.conversion;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {

	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{qty}")
	public CurrencyConversion convert(
			@PathVariable String from, @PathVariable String to, @PathVariable int qty) {
		
		HashMap<String, String> uriVars = new HashMap<>();
		uriVars.put("from", from);
		uriVars.put("to", to);
		
		
		ResponseEntity<CurrencyConversion> currency = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
				CurrencyConversion.class, uriVars);
		CurrencyConversion currencyBody = currency.getBody();
		
		return new CurrencyConversion(currencyBody.getId(), from, to, qty, currencyBody.getConversionMultiple()
				,currencyBody.getEnvironment(), currencyBody.getConversionMultiple().multiply(BigDecimal.valueOf(qty))
				);
	}
	
	@Autowired
	private CurrencyExchangeProxy proxy;
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{qty}")
	public CurrencyConversion convertFeign(
			@PathVariable String from, @PathVariable String to, @PathVariable int qty) {
		CurrencyConversion currencyBody = proxy.convert(from, to);
		
		return new CurrencyConversion(currencyBody.getId(), from, to, qty, currencyBody.getConversionMultiple()
				,currencyBody.getEnvironment(), currencyBody.getConversionMultiple().multiply(BigDecimal.valueOf(qty))
				);
	}
}
