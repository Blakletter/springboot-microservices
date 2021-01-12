package com.cancerup.mongoaccesslayer.models;

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
