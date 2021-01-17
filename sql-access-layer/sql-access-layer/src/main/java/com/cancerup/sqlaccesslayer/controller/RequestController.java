package com.cancerup.sqlaccesslayer.controller;

import com.cancerup.sqlaccesslayer.UserRepository;
import com.cancerup.sqlaccesslayer.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class RequestController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/requestuser", method= RequestMethod.POST)
    public ResponseEntity<User> getUser(@RequestBody String email) {
        Optional<User> response = userRepository.findByEmail(email);
        if (response.equals(Optional.empty())) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }
}

