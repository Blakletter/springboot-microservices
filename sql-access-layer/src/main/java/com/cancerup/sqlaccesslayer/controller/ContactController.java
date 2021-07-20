package com.cancerup.sqlaccesslayer.controller;

import com.cancerup.sqlaccesslayer.ContactRepository;
import com.cancerup.sqlaccesslayer.models.*;
import com.cancerup.sqlaccesslayer.util.CustomMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private CustomMapper mapper;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/createcontact")
    public ResponseEntity<Void> createContact(@RequestBody Contact contact)  {
        try {
            contactRepository.save(contact);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

        }

    }

    @PostMapping("/requestcontact")
    public ResponseEntity<Optional> requestContact(@RequestBody String firstName) {
        Optional<Contact> response = contactRepository.findByFirstName(firstName);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/contact")
    public List<Contact> all() {
        return contactRepository.findAllByPosition("position");
    }

    @DeleteMapping("/deletecontact")
    public ResponseEntity<Long> deleteContact(@RequestParam ("firstname") String firstName){
        long response = contactRepository.deleteByFirstName(firstName);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @RequestMapping(value="/updatecontact", method= RequestMethod.POST)
    public ResponseEntity<Optional> updateContact(@RequestBody Contact contact, @RequestParam long contactId)  {
        Optional<Contact> myContact = contactRepository.findByContactId(contactId);
        if(myContact.isPresent()){
            mapper.updateContactFromDto(contact, myContact.get());
            contactRepository.save(myContact.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(myContact);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // error 409
    }

//    @PutMapping("/editcontact")
//    public ResponseEntity<Void> editContact(@RequestBody Contact contact)  {
//        if (contactRepository.findByFirstName(contact.getFirstName()).equals(Optional.empty())) {
//            contactRepository.save(contact);
//            return ResponseEntity.status(HttpStatus.CREATED).body(null);
//        }
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
//    }
}
