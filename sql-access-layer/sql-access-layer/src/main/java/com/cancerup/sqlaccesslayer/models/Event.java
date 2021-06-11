// TODO :: fully implement contacts List { test && table }, look into JSON URL , Event Location { test } , 
package com.cancerup.sqlaccesslayer.models;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {
    @Id
    private long eventId;
    @NotNull
    private long userId;
    @NotNull
    private String eventName;
    @Nullable
    private String eventDescription;
    @NotNull
    private String eventDate;
    @Nullable
    private String eventStartTime;
    @Nullable
    private String eventEndTime;
    @Nullable
    private String location;
    @JoinTable // should be saved as EVENT_CONTACTS
    @OneToMany
    private List<Contact> contacts; // TODO {contacts} :: work on EVENT_CONTACT table.


    public Event(long userId,
                 String eventName,
                 String eventDescription,
                 String eventDate,
                 String eventStartTime,
                 String eventEndTime,
                 String location,
                 List<Contact> contacts) {
        this.userId = userId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.location = location;
        this.contacts = contacts;
    }

    // example raw JSON body for postman API /createevent :: { "userId":22, "eventName":"Meeting", "eventDate":"2021-05-20" }
    public Event(long userId,
                 String eventName,
                 String eventDate){
        this.userId = userId;
        this.eventName = eventName;
        this.eventDate = eventDate;
    }

    public Event(){ }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(String eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public String getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(String eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", userId=" + userId +
                ", eventName='" + eventName + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", eventStartTime='" + eventStartTime + '\'' +
                ", eventEndTime='" + eventEndTime + '\'' +
                ", location='" + location + '\'' +
                ", contacts=" + contacts +
                '}';
    }
}
