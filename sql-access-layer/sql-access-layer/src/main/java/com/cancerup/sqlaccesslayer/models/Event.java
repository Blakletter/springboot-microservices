package com.cancerup.sqlaccesslayer.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date eventDate;
    @Nullable
    private Time eventStartTime;
    @Nullable
    private Time eventEndTime;

    public Event(long userId,
                 String eventName,
                 String eventDescription,
                 Date eventDate,
                 Time eventStartTime,
                 Time eventEndTime) {
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
                 Date eventDate){
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

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getEventDate() {
        return eventDate;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public void setEventDate(String date) throws ParseException { this.eventDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(date); }

    public Time getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(Time eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public Time getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(Time eventEndTime) {
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
