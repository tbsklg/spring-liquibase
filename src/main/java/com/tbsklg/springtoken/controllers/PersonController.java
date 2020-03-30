package com.tbsklg.springtoken.controllers;

import com.tbsklg.springtoken.model.FinApiUserAccessToken;
import com.tbsklg.springtoken.model.Person;
import com.tbsklg.springtoken.services.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
public class PersonController {

    private final static Logger LOGGER = LoggerFactory.getLogger(PersonController.class);
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/api/v1/persons/{personId}/finapi")
    public ResponseEntity<Person> createPerson(@PathVariable @ValidPersonId String personId) {
        Optional<Person> mayBePerson = this.personService.createPerson(personId);

        if (!mayBePerson.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/api/v1/persons/{personId}")
    public ResponseEntity getPerson(@PathVariable @ValidPersonId String personId) {
        LOGGER.info(MessageFormat.format("request for personId{0}", personId));
        Optional<Person> mayBePerson = this.personService.getPerson(personId);

        if (!mayBePerson.isPresent()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(mayBePerson.get());
    }

    @GetMapping("/api/v1/persons")
    public ResponseEntity<List<Person>> getPersons() {
        List<Person> persons = this.personService.getPersons();

        if (persons.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(persons);
    }

    @GetMapping("/api/v1/persons/personIds")
    public ResponseEntity<List<String>> getPersonIds() {
        List<String> personIds = this.personService.getPersonIds();

        if (personIds.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(personIds);
    }


    @GetMapping("/api/v1/persons/{personId}/finapi/accesstoken")
    public ResponseEntity<FinApiUserAccessToken> getAccessToken(@PathVariable String personId) {
        Optional<FinApiUserAccessToken> mayBeAccessToken = this.personService.getAccessToken(personId);

        if (!mayBeAccessToken.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mayBeAccessToken.get());
    }
}
