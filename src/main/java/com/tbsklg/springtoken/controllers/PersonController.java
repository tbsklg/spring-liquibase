package com.tbsklg.springtoken.controllers;

import com.tbsklg.springtoken.model.FinApiUserAccessToken;
import com.tbsklg.springtoken.services.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/api/v1/persons/{personId}/finapi")
    public ResponseEntity<Void> createPerson(@PathVariable String personId){
       return this.personService.createPerson(personId);
    }

    @GetMapping("/api/v1/persons/{personId}/finapi/accesstoken")
    public ResponseEntity<FinApiUserAccessToken> getAccessToken(@PathVariable String personId){
        return this.personService.getAccessToken(personId);
    }
}
