package com.RESTAPI1.RESTAPI1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

public class Restapi1Application {

	public static void main(String[] args) {
		SpringApplication.run(Restapi1Application.class, args);
	}

}
