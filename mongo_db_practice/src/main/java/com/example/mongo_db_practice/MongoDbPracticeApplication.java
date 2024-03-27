package com.example.mongo_db_practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MongoDbPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoDbPracticeApplication.class, args);
	}

}
