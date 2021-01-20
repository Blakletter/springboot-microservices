package com.cancerup.mongoaccesslayer.controller;

import com.cancerup.mongoaccesslayer.MongoUserRepository;
import com.cancerup.mongoaccesslayer.models.DataAccess;
import com.cancerup.mongoaccesslayer.models.DataPutRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DeleteController {

    @Autowired
    private MongoUserRepository mongoUserRepository;

    @RequestMapping(value="/deletedata", method= RequestMethod.DELETE)
    public ResponseEntity<Void> deleteData(@RequestHeader(value = "ID") String id) {
        Optional<DataPutRequest> data = mongoUserRepository.findByDataAccess(new DataAccess(id));
        if (data.equals(Optional.empty())) {
            System.out.println("Could not find any data with id "+ id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        mongoUserRepository.delete(data.get());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}

