package com.cancerup.mongoaccesslayer.models;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

@Entity
public class DataPutRequest {
    @Id
    private com.cancerup.mongoaccesslayer.models.DataAccess dataAccess;
    private Object data;
    public DataPutRequest() {}

    public DataPutRequest(DataAccess dataAccess, Object data) {
        this.dataAccess = dataAccess;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public DataAccess getDataAccess() {
        return dataAccess;
    }

    public void setDataAccess(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }
}
