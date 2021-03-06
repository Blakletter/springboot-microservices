package com.cancerup.sqlaccesslayer.controller;
import com.cancerup.sqlaccesslayer.EventRepository;
import com.cancerup.sqlaccesslayer.models.*;
import com.cancerup.sqlaccesslayer.util.CustomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class EventController {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private CustomMapper mapper;

    @RequestMapping(value="/createevent", method= RequestMethod.POST)
    public ResponseEntity<Void> createEvent(@RequestBody Event event)  {
        if ((!Objects.isNull(event.getUserId())) && (event.getEventName()!=null) ){ //If either of these two things are null it will fail
            eventRepository.save(event);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    @RequestMapping(value="/requestevent", method= RequestMethod.POST)
    public ResponseEntity<Optional> requestEvent(@RequestParam long eventId) {
        Optional<Event> response = eventRepository.findByEventId(eventId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @RequestMapping(value="/requestevents", method= RequestMethod.POST)
    public ResponseEntity<Optional> requestEvents(@RequestParam long userId) {
        Optional<List<Event>> response = eventRepository.findAllByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @RequestMapping(value="/updateevent", method= RequestMethod.POST)
    public ResponseEntity<Optional> updateEvent(@RequestBody Event event, @RequestParam long eventId)  {
        Optional<Event> myEvent = eventRepository.findByEventId(eventId);
        if(myEvent.isPresent()){
            mapper.updateEventFromDto(event, myEvent.get());
            eventRepository.save(myEvent.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(myEvent); 
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // error 409
    }

    @RequestMapping(value="/deleteevents", method= RequestMethod.DELETE)
    public ResponseEntity<Long> deleteEvents(@RequestParam long userId){
        long response = eventRepository.deleteByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
