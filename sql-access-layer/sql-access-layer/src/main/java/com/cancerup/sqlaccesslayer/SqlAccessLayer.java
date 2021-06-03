package com.cancerup.sqlaccesslayer;


import com.cancerup.sqlaccesslayer.oauth.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import javax.sql.DataSource;

//EnableAuthorizationServer provides the actual security layer to our program
@EnableAuthorizationServer
@SpringBootApplication
@EnableJpaRepositories
@PropertySource({ "classpath:application.properties" })
public class SqlAccessLayer {

	//Define the connection values to our datasource (defined in application.properties)
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	@Value("${spring.datasource.url}")
	private String dataUrl;
	@Value("${spring.datasource.username}")
	private String dataUsername;
	@Value("${spring.datasource.password}")
	private String dataPassword;


	//Define our own password encryptor. BCrypt is very good.
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//Define our datasource. Note: This is not working, but it is for getting clients from our DB.
	// https://www.baeldung.com/spring-security-oauth-dynamic-client-registration
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(dataUrl);
		dataSource.setUsername(dataUsername);
		dataSource.setPassword(dataPassword);
		return dataSource;
	}


	//This is our CustomUserDetailsService that we want to use
	@Bean
	public CustomUserDetailsService customUserDetailsService() {
		return new CustomUserDetailsService();
	}



	public static void main(String[] args) {
		SpringApplication.run(SqlAccessLayer.class, args);
	}
}
