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
public class RequestController {

    @Autowired
    private MongoUserRepository mongoUserRepository;

    @RequestMapping(value="/requestdata", method= RequestMethod.POST)
    public ResponseEntity<Object> requestData(@RequestBody DataAccess dataAccess) {
        Optional<DataPutRequest> result = mongoUserRepository.findByDataAccess(dataAccess);
        if (result.equals(Optional.empty())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }
}
