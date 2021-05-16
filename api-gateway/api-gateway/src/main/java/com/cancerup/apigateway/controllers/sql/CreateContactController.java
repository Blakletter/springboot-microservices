package com.cancerup.apigateway.controllers.sql;
import com.cancerup.apigateway.models.Contact;
import com.sun.jersey.api.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@RestController
public class CreateContactController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value="/createcontact", method = RequestMethod.POST)
    public Contact createUser(@RequestBody Contact contact) throws ConflictException {
          return contact;
//        return webClientBuilder.build()
//                .post()
//                .uri("http://sql-access-layer/createcontact")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(contact)
//                .retrieve()
//                .toEntity(Void.class);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<Void> handleWebClientResponseException(WebClientResponseException ex) {
        return ResponseEntity.status(ex.getRawStatusCode()).body(null);
    }
}
