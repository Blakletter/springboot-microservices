package com.cancerup.sqlaccesslayer.controller;
import com.cancerup.sqlaccesslayer.UserRepository;
import com.cancerup.sqlaccesslayer.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UsersControllers {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value="/requestuser", method= RequestMethod.POST)
    public ResponseEntity<Optional> requestUser(@RequestBody String email) {
        Optional<User> response = userRepository.findByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @RequestMapping(value="/createuser", method= RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user)  {

        if (userRepository.findByEmail(user.getEmail()).equals(Optional.empty())) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword())); //Encode the password with bcrypt
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    @RequestMapping(value="/deleteuser", method= RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@RequestParam String email)  {

        if (userRepository.deleteByEmail(email)>0) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        };
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}

