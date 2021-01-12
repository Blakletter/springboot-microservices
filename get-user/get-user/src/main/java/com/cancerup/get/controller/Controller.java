package com.cancerup.get.controller;

import com.cancerup.get.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
public class Controller {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping(value="/get", method= RequestMethod.POST)
    public Mono<ResponseEntity<User>> get(@RequestBody User user)  {
            return webClientBuilder.build()
                    .post()
                    .uri("http://sql-access-layer/get")
                    .bodyValue(user)
                    .retrieve()
                    .toEntity(User.class);
    }
}

