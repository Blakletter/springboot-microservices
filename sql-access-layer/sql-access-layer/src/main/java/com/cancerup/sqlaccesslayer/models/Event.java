package com.cancerup.sqlaccesslayer.models;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "events")
public class Event {
    @Id
    private Long eventId;
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

    public Event(long userId,
                 String eventName,
                 String eventDescription,
                 String eventDate,
                 String eventStartTime,
                 String eventEndTime) {
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
                 String eventDate){
        this.userId = userId;
        this.eventName = eventName;
        this.eventDate = eventDate;
    }

    public Event(){ }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
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

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", userId=" + userId +
                ", eventName='" + eventName + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", eventDate=" + eventDate +
                ", eventStartTime=" + eventStartTime +
                ", eventEndTime=" + eventEndTime +
                '}';
    }
}
