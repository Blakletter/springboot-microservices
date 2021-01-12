package com.cancerup.front.controllers;

import com.cancerup.front.models.DataAccess;
import com.cancerup.front.models.DataPutRequest;
import com.cancerup.front.services.MyUserDetailsService;
import com.cancerup.front.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@RestController
public class GetDataController {

    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private MyUserDetailsService userDetailsService;

    @RequestMapping(value="/getdata", method = RequestMethod.GET)
    public Mono<ResponseEntity<Object>> getData(@RequestHeader("Authorization") String token) {
        if (token==null) return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
        String username = jwtUtil.extractUsername(token);
        if  (username==null) return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
        DataAccess dataAccess = new DataAccess(username);
        return webClientBuilder.build()
                .post()
                .uri("http://mongo-access-layer/getdata")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dataAccess)
                .retrieve()
                .toEntity(Object.class);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<Void> handleWebClientResponseException(WebClientResponseException ex) {
        return ResponseEntity.status(ex.getRawStatusCode()).body(null);
    }
}
