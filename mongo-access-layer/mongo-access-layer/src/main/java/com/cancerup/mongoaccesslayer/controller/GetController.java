package com.cancerup.mongoaccesslayer.controller;

import com.cancerup.mongoaccesslayer.MongoUserRepository;
import com.cancerup.mongoaccesslayer.models.DataAccess;
import com.cancerup.mongoaccesslayer.models.DataPutRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.cancerup.mongoaccesslayer.models.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
public class GetController {

    @Autowired
    private MongoUserRepository mongoUserRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping(value="/getdata", method= RequestMethod.POST)
    public ResponseEntity<Object> getData(@RequestBody DataAccess dataAccess) {
        ResponseEntity<User> response = webClientBuilder.build()
                .post()
                .uri("http://sql-access-layer/getuser")
                .bodyValue(dataAccess.getEmail())
                .retrieve()
                .toEntity(User.class).block();
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            DataAccess access = new DataAccess(response.getBody().getId());
            Optional<DataPutRequest> result = mongoUserRepository.findByDataAccess(access);
            if (result.equals(Optional.empty())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(result.get());
        }
       return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);

    }
}

