package com.cancerup.front.controllers.mongodb;

import com.cancerup.front.models.DataAccess;
import com.cancerup.front.models.DataPutRequest;
import com.cancerup.front.models.Task;
import com.cancerup.front.models.Tasks;
import com.cancerup.front.services.MyUserDetailsService;
import com.cancerup.front.util.JwtUtil;
import com.netflix.client.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
public class DeleteDataController {

    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @RequestMapping(value="/deletedata", method = RequestMethod.DELETE)
    public Mono<ResponseEntity<Void>> deleteData(@RequestHeader("Authorization") String token, @RequestBody(required = false) Object data) {

        if (token==null) return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
        String username = jwtUtil.extractUsername(token);
        if  (username==null) return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
        int id = jwtUtil.extractClaim(token, claims -> claims.get("id", Integer.class));
        String accessToken = String.valueOf(id);

        WebClient client = webClientBuilder.build();
        return webClientBuilder.build()
                .delete()
                .uri("http://mongo-access-layer/deletedata")
                .header("ID", accessToken)
                .retrieve()
                .toEntity(Void.class);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<Void> handleWebClientResponseException(WebClientResponseException ex) {
        return ResponseEntity.status(ex.getRawStatusCode()).body(null);
    }
}
