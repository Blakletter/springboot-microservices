package com.cancerup.sqlaccesslayer.models;

import com.sun.istack.Nullable;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "leads")
public class Lead {
    @Id
    private long leadId;
    @NotNull
    private long userId;
    @NotNull
    private String type;
    @NotNull
    private String name;
    @NotNull
    private String phone;
    @Nullable
    private String date;

    public Lead(long userId,
                String type,
                String name,
                String phone,
                String date) {
        this.userId = userId;
        this.type = type;
        this.name = name;
        this.phone = phone;
        this.date = date;
    }

    public Lead() {}

    public long getLeadId() {
        return leadId;
    }

    public void setLeadId(long leadId) {
        this.leadId = leadId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

