package com.cancerup.get;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GetUserService {

	@Bean
	@LoadBalanced
	public WebClient.Builder getWebClientBuilder() {
		HttpClient httpClient = HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
		return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient));
	}
	public static void main(String[] args) {
		SpringApplication.run(GetUserService.class, args);
	}

}
