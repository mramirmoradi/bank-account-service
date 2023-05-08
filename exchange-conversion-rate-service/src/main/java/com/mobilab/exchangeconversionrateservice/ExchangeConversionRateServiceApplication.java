package com.mobilab.exchangeconversionrateservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ExchangeConversionRateServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeConversionRateServiceApplication.class, args);
	}

}
