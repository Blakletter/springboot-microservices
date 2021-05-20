package com.cancerup.apigateway.controllers.sql;
import com.cancerup.apigateway.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@RestController
public class RequestUserController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value="/createuser")
    public Mono<ResponseEntity<Void>> createUser(@RequestBody User user) throws ConflictException {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return webClientBuilder.build()
                .post()
                .uri("http://sql-access-layer/createuser")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(user)
                .retrieve()
                .toEntity(Void.class);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/requestuser")
    public Mono<ResponseEntity<User>> get(@RequestBody User user)  {
        return webClientBuilder.build()
                .post()
                .uri("http://sql-access-layer/requestuser")
                .bodyValue(user)
                .retrieve()
                .toEntity(User.class);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<Void> handleWebClientResponseException(WebClientResponseException ex) {
        return ResponseEntity.status(ex.getRawStatusCode()).body(null);
    }
}
