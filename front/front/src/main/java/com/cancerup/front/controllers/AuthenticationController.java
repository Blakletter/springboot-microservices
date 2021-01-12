package com.cancerup.front.controllers;

import com.cancerup.front.models.AuthenticationRequest;
import com.cancerup.front.models.AuthenticationResponse;
import com.cancerup.front.services.MyUserDetails;
import com.cancerup.front.services.MyUserDetailsService;
import com.cancerup.front.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;


    @RequestMapping(value="/authenticate", method= RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        if (authenticationRequest == null) return (ResponseEntity<?>) ResponseEntity.badRequest();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            throw new Exception("Incorrect username or password", e);
        } catch (DisabledException e) {
            e.printStackTrace();
            throw new Exception("Your account is disabled");
        }
        catch (AccountExpiredException e){
            e.printStackTrace();;
            throw new Exception("You credentials are expired");
        }catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Something went wrong", e);
        }

        UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());

        if (userDetails==null) return (ResponseEntity<?>) ResponseEntity.notFound();
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
