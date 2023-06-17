package com.company.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfig {
	@Bean
	 public OpenAPI springShopOpenAPI() {
		
		Contact contact = new Contact();
	    contact.setName("Raul TS");
	    contact.setUrl("https://www.raultoledo.com");
	    
		return new OpenAPI()
	              .info(new Info().title("Sample CRUD API")
	              .description("Spring sample application")
	              .version("v0.0.1")
	              .license(new License().name("Apache 2.0").url("http://springdoc.org")).contact(contact));
	  }
}
