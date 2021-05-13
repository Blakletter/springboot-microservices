package com.cancerup.sqlaccesslayer.controller;
import com.cancerup.sqlaccesslayer.ContactRepository;
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
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @RequestMapping(value="/createcontact", method= RequestMethod.POST)
    public ResponseEntity<Void> createContact(@RequestBody Contact contact)  {
        if (contactRepository.findByFirstName(contact.getFirstName()).equals(Optional.empty())) {
            contactRepository.save(contact);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    @RequestMapping(value="/requestcontact", method= RequestMethod.POST)
    public ResponseEntity<Optional> requestContact(@RequestBody String firstName) {
        Optional<Contact> response = contactRepository.findByFirstName(firstName);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

