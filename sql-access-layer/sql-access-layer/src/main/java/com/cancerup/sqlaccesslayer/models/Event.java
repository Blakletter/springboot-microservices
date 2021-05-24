package com.cancerup.sqlaccesslayer.models;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.*;

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
    private LocalDate eventDate;
    @Nullable
    private LocalTime eventStartTime;
    @Nullable
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

    // example raw JSON body for postman API /createevent :: { "userId":22, "eventName":"Meeting", "eventDate":"2021-05-20" }
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

    public String getEventDescription() { return eventDescription; }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventDate(String date) { this.eventDate = LocalDate.parse(date); }

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
