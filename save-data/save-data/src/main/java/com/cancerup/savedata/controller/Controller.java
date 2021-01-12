package com.cancerup.savedata.controller;

import com.cancerup.savedata.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import javax.xml.crypto.Data;

@RestController
public class Controller {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping(value="/savedata", method= RequestMethod.POST)
    public Mono<ResponseEntity<Void>> savedata(@RequestBody DataPutRequest dataPutRequest){

        ResponseEntity<User> user = webClientBuilder.build()
                .post()
                .uri("http://sql-access-layer/getAsync")
                .bodyValue(dataPutRequest.getDataAccess().getEmail())
                .retrieve()
                .toEntity(User.class).block();

        DataPutRequest data = new DataPutRequest();
        data.setData(dataPutRequest.getData());
        data.setDataAccess(new DataAccess(user.getBody().getId()));
        return webClientBuilder.build()
                .post()
                .uri("http://mongo-access-layer/save")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(data)
                .retrieve()
                .toEntity(Void.class);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<Void> handleWebClientResponseException(WebClientResponseException ex) {
        return ResponseEntity.status(ex.getRawStatusCode()).body(null);
    }
}

