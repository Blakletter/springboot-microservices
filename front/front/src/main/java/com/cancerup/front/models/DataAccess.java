package com.cancerup.front.models;

import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public class DataAccess {
    private String token;

    public DataAccess(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public DataAccess() {
    }

}
