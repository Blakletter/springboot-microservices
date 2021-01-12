package com.cancerup.front.models;

import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public class DataAccess {
    private long id;
    private String email;

    public DataAccess(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DataAccess() {
    }

    public DataAccess(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
