package com.example.currency;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CurrencyExchangeRepo extends JpaRepository<CurrencyExchange, Integer> {

	CurrencyExchange findByFromAndTo(String from, String to);
}
