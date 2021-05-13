package com.cancerup.apigateway.controllers.sql;
import com.cancerup.apigateway.models.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@RestController
public class RequestContactController {
    @Autowired
    private WebClient.Builder webClientBuilder;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value="/requestcontact", method= RequestMethod.POST)
    public Mono<ResponseEntity<Contact>> get(@RequestBody Contact contact)  {
        return webClientBuilder.build()
                .post()
                .uri("http://sql-access-layer/requestcontact")
                .bodyValue(contact)
                .retrieve()
                .toEntity(Contact.class);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<Void> handleWebClientResponseException(WebClientResponseException ex) {
        return ResponseEntity.status(ex.getRawStatusCode()).body(null);
    }
}
