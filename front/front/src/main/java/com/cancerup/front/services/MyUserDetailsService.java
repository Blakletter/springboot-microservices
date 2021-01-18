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
    public MyUserDetails loadUserByUsername(String email){
        User user = webClientBuilder.build()
                .post()
                .uri("http://sql-access-layer/requestuser")
                .bodyValue(email)
                .retrieve()
                .toEntity(User.class).block().getBody();
        if (user==null) return new MyUserDetails();
        return new MyUserDetails(user);
    }


}
