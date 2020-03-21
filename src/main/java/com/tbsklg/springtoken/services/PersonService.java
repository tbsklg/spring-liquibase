package com.tbsklg.springtoken.services;

import com.tbsklg.springtoken.model.*;
import com.tbsklg.springtoken.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    private PersonRepository personRepository;
    private FinAPIClient finAPI;

    public PersonService(PersonRepository personRepository, FinAPIClient finAPI) {
        this.personRepository = personRepository;
        this.finAPI = finAPI;
    }

    public ResponseEntity<Void> createPerson(String personIdAsText) {
        PersonId personId = PersonId.of(personIdAsText);
        Optional<Person> mayBePerson = personRepository.findById(personId.getValue());

        if(!mayBePerson.isPresent()){
            FinApiUser finApiUser = finAPI.createPerson();
            Person person = Person.from(personId, finApiUser.getUsername(), finApiUser.getPassword());
            personRepository.save(person);
            return ResponseEntity.created(null).build();
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    public ResponseEntity<FinApiUserAccessToken> getAccessToken(String personIdAsText) {
        PersonId personId = PersonId.of(personIdAsText);
        Optional<Person> mayBePerson = personRepository.findById(personId.getValue());

        if(!mayBePerson.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        FinApiAccessToken finAPIAccessToken = finAPI.getAccessToken();
        FinApiUserAccessToken finApiUserAccessToken =
                new FinApiUserAccessToken(finAPIAccessToken.getAccessToken());

        return new ResponseEntity<>(finApiUserAccessToken, HttpStatus.OK);
    }
}
