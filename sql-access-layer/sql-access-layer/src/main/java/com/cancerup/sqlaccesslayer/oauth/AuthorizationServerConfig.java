package com.cancerup.sqlaccesslayer.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;



    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

        security.allowFormAuthenticationForClients().passwordEncoder(passwordEncoder);
    }

    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {


        clients.inMemory()
                .withClient("client")
                .authorizedGrantTypes("authorization_code", "refresh_token", "password")
                .authorities("USER")
                .scopes("all")
                .secret(passwordEncoder.encode("secret"));

//    clients
//                .jdbc(this.dataSource)
//                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder())
//                .clients(new InMemoryClientDetailsService());
    }
}
