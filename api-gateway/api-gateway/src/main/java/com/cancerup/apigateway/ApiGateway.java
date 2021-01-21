package com.cancerup.apigateway;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;



@SpringBootApplication(scanBasePackages= {"com.cancerup.apigateway"})
@EnableDiscoveryClient
public class ApiGateway {

	@Bean
	@LoadBalanced
	public WebClient.Builder getWebClientBuilder() {
		HttpClient httpClient = HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
		return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient));
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiGateway.class, args);
	}
}
