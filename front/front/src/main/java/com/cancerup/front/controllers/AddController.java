package com.cancerup.front.controllers;
import com.cancerup.front.models.User;
import com.netflix.ribbon.proxy.annotation.Http;
import com.sun.jersey.api.ConflictException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;

@RestController
public class AddController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping(value="/adduser", method = RequestMethod.POST)
    public Mono<ResponseEntity<Void>> adduser(@RequestBody User user) throws ConflictException {
            return webClientBuilder.build()
                    .post()
                    .uri("http://add-user/add")
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
