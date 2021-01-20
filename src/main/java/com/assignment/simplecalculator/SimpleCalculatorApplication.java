package com.assignment.simplecalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EntityScan("com.assignment.simplecalculator.model")
@SpringBootApplication
@EnableJpaRepositories
public class SimpleCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleCalculatorApplication.class, args);
	}

}
