package com.cancerup.mongoaccesslayer.models;

public class DataAccess {
    private long id;

    public DataAccess() {
    }

    public DataAccess(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
