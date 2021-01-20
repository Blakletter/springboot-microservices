package com.cancerup.apigateway.controllers;

import com.cancerup.apigateway.models.AuthenticationRequest;
import com.cancerup.apigateway.models.AuthenticationResponse;
import com.cancerup.apigateway.services.MyUserDetails;
import com.cancerup.apigateway.services.MyUserDetailsService;
import com.cancerup.apigateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value="/authenticate", method= RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        if (authenticationRequest == null) return (ResponseEntity<?>) ResponseEntity.badRequest();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        MyUserDetails userDetails = new MyUserDetails();
        userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());
        /*
        try {

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getCause());
        }
        if (userDetails.getUsername()==null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
*/
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<Void> handleWebClientResponseException(WebClientResponseException ex) {
        return ResponseEntity.status(ex.getRawStatusCode()).body(null);
    }
}
