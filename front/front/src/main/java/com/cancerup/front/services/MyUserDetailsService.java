package com.cancerup.front.services;

import com.cancerup.front.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public MyUserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User user = webClientBuilder.build()
                .post()
                .uri("http://sql-access-layer/requestuser")
                .bodyValue(email)
                .retrieve()
                .toEntity(User.class).block().getBody();
        if (user.equals(Optional.empty())) throw new UsernameNotFoundException("Username not found");
        return new MyUserDetails(user);
    }
/*
    public Mono<ResponseEntity<User>> loadUserByUsernameAsync(String email) throws UsernameNotFoundException{
        Mono<ResponseEntity<User>> user = webClientBuilder.build()
                .post()
                .uri("http://sql-access-layer/getAsync")
                .bodyValue(email)
                .retrieve()
                .toEntity(User.class);
        return user;
    }

 */
}
