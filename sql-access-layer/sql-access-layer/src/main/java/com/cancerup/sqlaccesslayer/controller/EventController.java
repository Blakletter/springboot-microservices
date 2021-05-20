package com.cancerup.sqlaccesslayer.controller;
import com.cancerup.sqlaccesslayer.EventRepository;
import com.cancerup.sqlaccesslayer.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @RequestMapping(value="/createevent", method= RequestMethod.POST)
    public ResponseEntity<Void> createEvent(@RequestBody Event event)  {
        if (event.userId && event.eventName) //If either of these two things are null it will fail
            eventRepository.save(event);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    @RequestMapping(value="/requestevent", method= RequestMethod.POST)
    public ResponseEntity<Optional> requestEvent(@RequestBody long eventId) {
        Optional<Event> response = eventRepository.findByEventId(eventId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
