package com.cancerup.sqlaccesslayer.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // next line can cause same stack overflow in case of non-existent user tries login.
                .parentAuthenticationManager(authenticationManagerBean())
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

        super.configure(auth);
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        //Here we need to define our CustomUserDetails
        return userDetailsService;

//        return new InMemoryUserDetailsManager(
//                User.withDefaultPasswordEncoder()
//                        .username("user2")
//                        .password("password2")
//                        .roles("ADMIN")
//                        .build());
    }
}
