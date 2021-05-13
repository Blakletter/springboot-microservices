package com.cancerup.apigateway.models;

public class Contact {
    private long id;
    private String firstName;
    private String lastName;
    private String position;
    private String industry;
    private long phoneNumber;
    private String website;
    private String city;
    private String state;
    private String zipcode;
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

    public String getEmail() { return email;}

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
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

    @java.lang.Override
    public java.lang.String toString() {
        return "Contact{" +
                "id=" + id +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", industry='" + industry + '\'' +
                ", phone_number=" + phoneNumber +
                ", website='" + website + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
