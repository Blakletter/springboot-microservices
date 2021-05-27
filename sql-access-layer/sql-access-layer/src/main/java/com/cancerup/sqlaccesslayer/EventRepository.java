package com.cancerup.sqlaccesslayer;

import com.cancerup.sqlaccesslayer.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("select u from Event u where u.userId = :userId")
    Optional<List<Event>> findAllByUserId(@Param("userId") long userId); // look into the Optional<Contact> more as with this we'd primarily want to return a list of events

    @Query("select u from Event u where u.eventId = :eventId")
    Optional<Event> findByEventId(@Param("eventId") long eventId);

    @Transactional
    long deleteByUserId(@Param("userId") long userId);
}
