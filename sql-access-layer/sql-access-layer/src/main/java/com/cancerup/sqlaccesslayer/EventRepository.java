package com.cancerup.sqlaccesslayer;

import com.cancerup.sqlaccesslayer.models.Event;
import com.cancerup.sqlaccesslayer.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findByUserId(long userId); // look into the Optional<Contact> more as with this we'd primarily want to return a list of events
    Optional<Event> findByEventId(long eventId);
}
