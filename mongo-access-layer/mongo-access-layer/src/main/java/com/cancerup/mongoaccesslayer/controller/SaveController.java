package com.cancerup.mongoaccesslayer.controller;

import com.cancerup.mongoaccesslayer.MongoUserRepository;
import com.cancerup.mongoaccesslayer.models.DataPutRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaveController {

    @Autowired
    private MongoUserRepository mongoUserRepository;

    @RequestMapping(value="/save", method= RequestMethod.POST)
    public ResponseEntity<Void> save(@RequestBody DataPutRequest dataPutRequest) {
        DataPutRequest result = mongoUserRepository.save(dataPutRequest);
        if (result==null) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}

