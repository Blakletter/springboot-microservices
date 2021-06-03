package com.cancerup.sqlaccesslayer.oauth;

import com.cancerup.sqlaccesslayer.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;


//The user details service is what spring uses to try and find the accounts. We just implement it and override the methods to find them ourself
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //Query our DB
        Optional<com.cancerup.sqlaccesslayer.models.User> user = userRepository.findByEmail(s);
        //Did it find something?
        if (user.isEmpty()) {
            //Throw an uh-oh
            throw new UsernameNotFoundException("Email not found");
        } else {
            //Get the user from the Optional
            com.cancerup.sqlaccesslayer.models.User account = user.get();
            //Create the customerUserDetails which is what spring security wants
            return User.withUserDetails(new CustomUserDetails(account.getEmail(), account.getPassword())).build();
        }
    }
}
