package com.mobilab.mobilab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MobilabApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobilabApplication.class, args);
	}

}
