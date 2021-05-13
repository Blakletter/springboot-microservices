package com.cancerup.sqlaccesslayer.models;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    private long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Nullable
    private String position;
    @Nullable
    private String industry;
    @Nullable
    private long phoneNumber;
    @Nullable
    private String website;
    @Nullable
    private String city;
    @Nullable
    private String state;
    @Nullable
    private String zipcode;
    @Nullable
    private String email;

    public Contact(String firstName, String lastName, String position, String industry, long phoneNumber, String website, String city, String state, String zipcode, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.industry = industry;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.email = email;
    }

    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Contact() {}

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public String getIndustry() {
        return industry;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getEmail() {
        return email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirst_name(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
