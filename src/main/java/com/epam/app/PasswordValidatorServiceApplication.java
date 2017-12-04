package com.epam.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.epam.*"})
@SpringBootApplication
public class PasswordValidatorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PasswordValidatorServiceApplication.class, args);
	}

}

