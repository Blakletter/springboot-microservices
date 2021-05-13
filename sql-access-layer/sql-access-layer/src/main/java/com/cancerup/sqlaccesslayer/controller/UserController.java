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
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/createuser", method= RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user)  {
        if (userRepository.findByEmail(user.getEmail()).equals(Optional.empty())) {
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    @RequestMapping(value="/requestuser", method= RequestMethod.POST)
    public ResponseEntity<Optional> requestUser(@RequestBody String email) {
        Optional<User> response = userRepository.findByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
