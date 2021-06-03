package com.cancerup.sqlaccesslayer;


import com.cancerup.sqlaccesslayer.oauth.CustomUserDetailsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableAuthorizationServer
@SpringBootApplication
@EnableJpaRepositories
public class SqlAccessLayer {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CustomUserDetailsService customUserDetailsService() {
		return new CustomUserDetailsService();
	}


	public static void main(String[] args) {
		SpringApplication.run(SqlAccessLayer.class, args);
	}
}
