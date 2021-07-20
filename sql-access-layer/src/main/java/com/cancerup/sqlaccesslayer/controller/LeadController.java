package com.cancerup.sqlaccesslayer.controller;
import com.cancerup.sqlaccesslayer.util.CustomMapper;
import org.springframework.http.HttpStatus;
import com.cancerup.sqlaccesslayer.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cancerup.sqlaccesslayer.models.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class LeadController {

    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private CustomMapper mapper;

    @RequestMapping(value="/requestleads", method= RequestMethod.POST)
    public ResponseEntity<Optional> requestLeads(@RequestParam long userId ) {
        Optional<List<Lead>> response = leadRepository.findAllByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @RequestMapping(value="/createlead", method= RequestMethod.POST)
    public ResponseEntity<Void> createLead(@RequestBody Lead lead)  {
        if ((!Objects.isNull(lead.getUserId())) && (lead.getName()!=null)) {
            leadRepository.save(lead);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    @RequestMapping(value="/deletelead", method= RequestMethod.DELETE)
    public ResponseEntity<Long> deleteLead(@RequestParam long leadId)  {
        long response = leadRepository.deleteByLeadId(leadId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @RequestMapping(value="/updatelead",method=RequestMethod.POST)
    public ResponseEntity<Optional>updateLead(@RequestBody Lead lead,@RequestParam long leadId){
        Optional<Lead>myLead=leadRepository.findByLeadId(leadId);
        if(myLead.isPresent()){
            mapper.updateLeadFromDto(lead,myLead.get());
            leadRepository.save(myLead.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(myLead);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);//error409
    }

}
