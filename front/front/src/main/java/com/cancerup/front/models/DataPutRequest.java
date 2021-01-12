package com.cancerup.front.models;
public class DataPutRequest {

    private DataAccess dataAccess;
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
