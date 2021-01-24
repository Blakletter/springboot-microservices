package com.cancerup.apigateway.controllers;

import com.cancerup.apigateway.models.AuthenticationRequest;
import com.cancerup.apigateway.models.AuthenticationResponse;
import com.cancerup.apigateway.services.MyUserDetails;
import com.cancerup.apigateway.services.MyUserDetailsService;
import com.cancerup.apigateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RestController
public class AuthenticationCheckController {
    @Autowired
    private JwtUtil jwtUtil;

    @CrossOrigin(origins="http://localhost:3000")
    @RequestMapping(value="/checkauthenticated", method= RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> checkAuthenticated(@RequestHeader("Authorization") String token) {
        String name = jwtUtil.extractClaim(token, claims -> claims.get("name", String.class));
        return ResponseEntity.status(HttpStatus.OK).body(new AuthenticationResponse(null, name));
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<Void> handleWebClientResponseException(WebClientResponseException ex) {
        return ResponseEntity.status(ex.getRawStatusCode()).body(null);
    }
}
