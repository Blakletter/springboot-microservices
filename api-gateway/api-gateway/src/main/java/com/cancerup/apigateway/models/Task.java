package com.cancerup.apigateway.models;

import java.time.LocalDate;

public class Task {
    private String item;
    private String status;
    private LocalDate date;

    public Task(String item, String status, LocalDate date) {
        this.item = item;
        this.status = status;
        this.date = date;
    }


    public Task() {
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
