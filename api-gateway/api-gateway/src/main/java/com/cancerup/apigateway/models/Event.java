package com.cancerup.apigateway.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event{

    private long eventId;
    private long userId;
    private String eventName;
    private String eventDescription;
    private LocalDate eventDate;
    private LocalTime eventStartTime;
    private LocalTime eventEndTime;

    public Event(long userId,
                 String eventName,
                 String eventDescription,
                 LocalDate eventDate,
                 LocalTime eventStartTime,
                 LocalTime eventEndTime) {
        this.userId = userId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
    }

    public Event(long userId,
                 String eventName,
                 LocalDate eventDate){
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

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public LocalTime getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(LocalTime eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public LocalTime getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(LocalTime eventEndTime) {
        this.eventEndTime = eventEndTime;
    }
}