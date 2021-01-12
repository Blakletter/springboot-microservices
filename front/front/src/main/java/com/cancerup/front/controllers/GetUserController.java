package com.cancerup.front.controllers;
import com.cancerup.front.models.User;
import com.sun.jersey.api.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@RestController
public class GetUserController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping(value="/getuser", method= RequestMethod.POST)
    public Mono<ResponseEntity<User>> get(@RequestBody User user)  {
        return webClientBuilder.build()
                .post()
                .uri("http://sql-access-layer/getuser")
                .bodyValue(user)
                .retrieve()
                .toEntity(User.class);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<Void> handleWebClientResponseException(WebClientResponseException ex) {
        return ResponseEntity.status(ex.getRawStatusCode()).body(null);
    }
}
