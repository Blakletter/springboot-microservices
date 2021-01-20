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
public class CreateController {

    @Autowired
    private MongoUserRepository mongoUserRepository;

    @RequestMapping(value="/createdata", method= RequestMethod.POST)
    public ResponseEntity<Void> createData(@RequestBody DataPutRequest dataPutRequest) {
        if (!mongoUserRepository.findByDataAccess(dataPutRequest.getDataAccess()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        DataPutRequest saved = mongoUserRepository.save(dataPutRequest);
        if (saved==null) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}

