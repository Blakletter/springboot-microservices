package com.cancerup.mongoaccesslayer.controller;

import com.cancerup.mongoaccesslayer.MongoUserRepository;
import com.cancerup.mongoaccesslayer.models.DataPutRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.util.Optional;

@RestController
public class UpdateController {

    @Autowired
    private MongoUserRepository mongoUserRepository;

    @RequestMapping(value="/updatedata", method= RequestMethod.PUT)
    public ResponseEntity<Void> updateData(@RequestBody DataPutRequest dataPutRequest) {
        Optional<DataPutRequest> data = mongoUserRepository.findByDataAccess(dataPutRequest.getDataAccess());
        if (data.equals(Optional.empty())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        DataPutRequest updateData = data.get();
        updateData.setData(dataPutRequest.getData());
        DataPutRequest saved = mongoUserRepository.save(updateData);
        if (saved==null) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}

