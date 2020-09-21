package com.arpit.crud.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class SpringBootFirstAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFirstAppApplication.class, args);
	}

}
