package com.cancerup.getdata.controller;

import com.cancerup.getdata.models.*;
import com.sun.jersey.api.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@RestController
public class Controller {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping(value="/getdata", method= RequestMethod.POST)
    public Mono<ResponseEntity<Object>> getData(@RequestBody DataAccess dataAccess) {
        ResponseEntity<User> response = webClientBuilder.build()
                .post()
                .uri("http://sql-access-layer/get")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dataAccess.getEmail())
                .retrieve()
                .toEntity(User.class).block();
        DataAccess access = new DataAccess(response.getBody().getId());
        return webClientBuilder.build()
                    .post()
                    .uri("http://mongo-access-layer/get")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(access)
                    .retrieve()
                    .toEntity(Object.class);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<Void> handleWebClientResponseException(WebClientResponseException ex) {
        return ResponseEntity.status(ex.getRawStatusCode()).body(null);
    }
}

