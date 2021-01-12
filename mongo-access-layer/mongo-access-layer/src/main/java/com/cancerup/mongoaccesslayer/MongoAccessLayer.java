package com.cancerup.mongoaccesslayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableMongoRepositories
@EnableDiscoveryClient
public class MongoAccessLayer {

	public static void main(String[] args) {
		SpringApplication.run(MongoAccessLayer.class, args);
	}

}
