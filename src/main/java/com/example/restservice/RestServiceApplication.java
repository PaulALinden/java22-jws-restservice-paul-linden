package com.example.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * The RestServiceApplication class is the entry point of the application.
 * It uses Spring Boot's @SpringBootApplication annotation to enable
 * auto-configuration and component scanning. This class contains the
 * main method, which starts the application.
 */
@SpringBootApplication
public class RestServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(RestServiceApplication.class, args);
	}
}
