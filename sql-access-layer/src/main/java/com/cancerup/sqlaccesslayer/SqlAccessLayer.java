package com.cancerup.sqlaccesslayer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SqlAccessLayer {
	public static void main(String[] args) {
		SpringApplication.run(SqlAccessLayer.class, args);
	}
}
