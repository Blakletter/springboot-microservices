package com.cancerup.apigateway.models;

public class Contact {
    private long id;
    private String first_name;
    private String last_name;
    private String position;
    private String industry;
    private long phone_number;
    private String website;
    private String city;
    private String state;
    private String zipcode;

    public Contact(long id, String first_name, String last_name, String position, String industry, long phone_number, String website, String city, String state, String zipcode) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.position = position;
        this.industry = industry;
        this.phone_number = phone_number;
        this.website = website;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    public long getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPosition() {
        return position;
    }

    public String getIndustry() {
        return industry;
    }

    public long getPhone_number() {
        return phone_number;
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

    public void setId(long id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
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

    @java.lang.Override
    public java.lang.String toString() {
        return "Contact{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", position='" + position + '\'' +
                ", industry='" + industry + '\'' +
                ", phone_number=" + phone_number +
                ", website='" + website + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
