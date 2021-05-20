package com.cancerup.apigateway;

import com.cancerup.apigateway.models.Contact;
import com.netflix.ribbon.proxy.annotation.Http;
import org.apache.coyote.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiGatewayTests {
	@Autowired
	private ApiGateway controller;

	@LocalServerPort
	private int port;

	@Autowired(required = true)
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	public void checkCreateContact() throws Exception {
		String url = "http://localhost:" + port + "/createcontact";
		Contact body = new Contact();
		ResponseEntity response = ResponseEntity.status(HttpStatus.OK).body(null);
		assertThat(this.restTemplate.postForObject(url, body, ResponseEntity.class)).isNotNull().isEqualTo(response);
	}

}
