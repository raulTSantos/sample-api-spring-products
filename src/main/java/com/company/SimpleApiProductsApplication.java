package com.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
public class SimpleApiProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleApiProductsApplication.class, args);
	}

}
