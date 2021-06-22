package com.cancerup.sqlaccesslayer;

import com.cancerup.sqlaccesslayer.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByFirstName(String firstName);

    @Query("select u from Contact u where u.contactId = :contactId")
    Optional<Contact> findByContactId(@Param("contactId") long contactId);

    List<Contact> findAllByPosition(String Position);

    @Transactional
    long deleteByFirstName(String firstName);
}