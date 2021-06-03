package com.cancerup.sqlaccesslayer.oauth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        return User.withDefaultPasswordEncoder()
//                        .username("user2")
//                        .password("password2")
//                        .roles("ADMIN")
//
//                        .build();
        return new CustomUserDetails();
    }
}
