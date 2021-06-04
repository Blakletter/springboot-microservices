package com.cancerup.sqlaccesslayer.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;

import javax.sql.DataSource;
import java.util.Map;

@Configuration

public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).userDetailsService(customUserDetailsService);
    }

    @Autowired
    DataSource dataSource;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {


        //In memory clients work for now, but we want to have multiple clients and manage it from our DB so this will not work for prod
        //For some reason it doesn't matter what client we send over, it does not work
        /*
        clients.inMemory()
                .withClient("client")
                .secret(bCryptPasswordEncoder.encode("secret"))
                .authorizedGrantTypes("authorization_code", "refresh_token", "password")
                .authorities("USER")
                .scopes("read", "write");
        */
        //Try to configure a datasource to load our clients from (so we can store them in our DB)
        // Stack overflow link :: https://stackoverflow.com/questions/35039656/how-to-add-a-client-using-jdbc-for-clientdetailsserviceconfigurer-in-spring
        clients.jdbc(dataSource)
                .withClient("client")
                .authorizedGrantTypes("authorization_code", "refresh_token", "password")
                .authorities("USER")
                .scopes("read", "write")
                .secret(bCryptPasswordEncoder.encode("secret")).and().build();

    }
}
