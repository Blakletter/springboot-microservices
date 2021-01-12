package com.cancerup.mongoaccesslayer.controller;

import com.cancerup.mongoaccesslayer.MongoUserRepository;
import com.cancerup.mongoaccesslayer.models.DataAccess;
import com.cancerup.mongoaccesslayer.models.DataPutRequest;
import com.cancerup.mongoaccesslayer.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class SaveController {

    @Autowired
    private MongoUserRepository mongoUserRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping(value="/savedata", method= RequestMethod.POST)
    public ResponseEntity<Void> saveData(@RequestBody DataPutRequest dataPutRequest) {
        //In the future, embed the user id in the jwt token so that we don't have to make an extra call
        //This is an Async save, so if we get the request
        // we assume that the sql-access-layer service is running (no error checking, which is an issue)
        ResponseEntity<User> response = webClientBuilder.build()
                .post()
                .uri("http://sql-access-layer/getuser")
                .bodyValue(dataPutRequest.getDataAccess().getEmail())
                .retrieve()
                .toEntity(User.class).block();
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            User user = response.getBody();
            DataPutRequest data = new DataPutRequest();
            data.setData(dataPutRequest.getData());
            data.setDataAccess(new DataAccess(user.getId()));
            DataPutRequest saved = mongoUserRepository.save(data);
            if (saved==null) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        }
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
    }
}

