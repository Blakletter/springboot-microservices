package com.cancerup.apigateway.models;

public class AuthenticationResponse {
    private final String jwt;
    private final String name;

    public AuthenticationResponse(String jwt, String name) {
        this.jwt = jwt; this.name=name;
    }

    public String getName() {return name;}

    public String getJwt() {return jwt;}
}
