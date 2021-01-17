package com.cancerup.front.controllers.mongodb;
import com.cancerup.front.models.*;
import com.cancerup.front.services.MyUserDetails;
import com.cancerup.front.services.MyUserDetailsService;
import com.cancerup.front.util.JwtUtil;
import com.sun.jersey.api.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import javax.ws.rs.core.Response;
import java.time.LocalDate;

@RestController
public class CreateDataController {

    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @RequestMapping(value="/createdata", method = RequestMethod.POST)
    public Mono<ResponseEntity<Void>> createData(@RequestHeader("Authorization") String token, @RequestBody(required = false) Object data) {

        if (token==null) return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
        String username = jwtUtil.extractUsername(token);
        if  (username==null) return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
        int id = jwtUtil.extractClaim(token, claims -> claims.get("id", Integer.class));
        String accessToken = String.valueOf(id);//new BCryptPasswordEncoder().encode(id+":"+username);

        DataPutRequest dataPutRequest = new DataPutRequest();
        dataPutRequest.setDataAccess(new DataAccess(accessToken));
        if (data==null) {
            //Construct the data we want to save (assume none has been passed)
            Tasks tasks = new Tasks();
            tasks.addTask(new Task("Clean up the park", "ONGOING", LocalDate.now()));
            tasks.addTask(new Task("Finish microservices", "COMPLETE", LocalDate.now().minusDays(3)));
            tasks.addTask(new Task("Buy 1567 kittens", "ONGOING", LocalDate.now().plusMonths(3)));
            dataPutRequest.setData(tasks);
        } else {
            dataPutRequest.setData(data);
        }
        return webClientBuilder.build()
                .post()
                .uri("http://mongo-access-layer/createdata")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dataPutRequest)
                .retrieve()
                .toEntity(Void.class);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<Void> handleWebClientResponseException(WebClientResponseException ex) {
        return ResponseEntity.status(ex.getRawStatusCode()).body(null);
    }
}
