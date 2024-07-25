package com.insideme.insidemebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.insideme.insidemebackend.repository")
@SpringBootApplication
public class InsidemeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsidemeBackendApplication.class, args);
	}

}
