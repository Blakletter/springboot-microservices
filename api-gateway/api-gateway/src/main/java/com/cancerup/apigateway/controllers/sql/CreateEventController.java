package com.cancerup.apigateway.controllers.sql;
import com.cancerup.apigateway.models.Event;
import com.sun.jersey.api.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@RestController
public class CreateEventController {
    @Autowired
    private WebClient.Builder webClientBuilder;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value="/createevent", method = RequestMethod.POST)
    public Mono<ResponseEntity<Void>> createUser(@RequestBody Event event) throws ConflictException {

        return webClientBuilder.build()
                .post()
                .uri("http://sql-access-layer/createevent")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(event)
                .retrieve()
                .toEntity(Void.class);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<Void> handleWebClientResponseException(WebClientResponseException ex) {
        return ResponseEntity.status(ex.getRawStatusCode()).body(null);
    }
}
