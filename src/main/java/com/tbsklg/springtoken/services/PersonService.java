package com.tbsklg.springtoken.services;

import com.tbsklg.springtoken.model.FinApiAccessToken;
import com.tbsklg.springtoken.model.FinApiUserAccessToken;
import com.tbsklg.springtoken.model.Person;
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

    public ResponseEntity<Void> createPerson(String personId) {
        Optional<Person> mayBePerson = personRepository.findByPersonId(personId);

        if(!mayBePerson.isPresent()){
            Person person = finAPI.createPerson(personId);
            personRepository.save(person);
            return ResponseEntity.created(null).build();
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    public ResponseEntity<FinApiUserAccessToken> getAccessToken(String personId) {
        Optional<Person> mayBePerson = personRepository.findByPersonId(personId);

        if(!mayBePerson.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        FinApiAccessToken finAPIAccessToken = finAPI.getAccessToken(mayBePerson.get());
        FinApiUserAccessToken finApiUserAccessToken =
                new FinApiUserAccessToken(finAPIAccessToken.getAccessToken());

        return new ResponseEntity<>(finApiUserAccessToken, HttpStatus.OK);
    }
}
