package com.cancerup.sqlaccesslayer.controller;

import com.cancerup.sqlaccesslayer.models.*;
import com.sun.jersey.api.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@RestController
public class Controller {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public Mono<ResponseEntity<Void>> add(@RequestBody User user) throws ConflictException {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        return webClientBuilder.build()
                    .post()
                    .uri("http://sql-access-layer/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(user)
                    .retrieve()
                    .toEntity(Void.class);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<Void> handleWebClientResponseException(WebClientResponseException ex) {
        return ResponseEntity.status(ex.getRawStatusCode()).body(null);
    }
}
