package com.cancerup.sqlaccesslayer.controller;
import com.cancerup.sqlaccesslayer.ContactRepository;
import com.cancerup.sqlaccesslayer.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Transactional
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @PostMapping("/createcontact")
    public ResponseEntity<Void> createContact(@RequestBody Contact contact)  {
        if (contactRepository.findByFirstName(contact.getFirstName()).equals(Optional.empty())) {
            contactRepository.save(contact);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
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

//    @PutMapping("/editcontact")
//    public ResponseEntity<Void> editContact(@RequestBody Contact contact)  {
//        if (contactRepository.findByFirstName(contact.getFirstName()).equals(Optional.empty())) {
//            contactRepository.save(contact);
//            return ResponseEntity.status(HttpStatus.CREATED).body(null);
//        }
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
//    }

}

