package com.raviteja.springframwork.BeerOrderService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile("localmysql")
public class BeerOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeerOrderServiceApplication.class, args);
	}

}
