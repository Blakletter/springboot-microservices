package com.cancerup.sqlaccesslayer;

import com.cancerup.sqlaccesslayer.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByFirstName(String firstName);

    List<Contact> findAllByPosition(String Position);

    long deleteByFirstName(String firstName);
}
