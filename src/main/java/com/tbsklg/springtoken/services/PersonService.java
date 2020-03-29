package com.tbsklg.springtoken.services;

import com.tbsklg.springtoken.model.*;
import com.tbsklg.springtoken.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private PersonRepository personRepository;
    private FinAPIClient finAPI;

    public PersonService(PersonRepository personRepository, FinAPIClient finAPI) {
        this.personRepository = personRepository;
        this.finAPI = finAPI;
    }

    public Optional<Person> createPerson(String personIdAsText) {
        PersonId personId = PersonId.of(personIdAsText);
        Optional<Person> mayBePerson = personRepository.findById(personId.getValue());

        if (!mayBePerson.isPresent()) {
            FinApiUser finApiUser = finAPI.createUser();
            Person person = Person.from(personId, finApiUser.getUsername(), finApiUser.getPassword());
            personRepository.save(person);
            return Optional.of(person);
        }

        return Optional.empty();
    }

    public Optional<FinApiUserAccessToken> getAccessToken(String personIdAsText) {
        PersonId personId = PersonId.of(personIdAsText);
        Optional<Person> mayBePerson = personRepository.findById(personId.getValue());

        if (!mayBePerson.isPresent()) {
            return Optional.empty();
        }

        FinApiAccessToken finAPIAccessToken = finAPI.getAccessToken();
        FinApiUserAccessToken finApiUserAccessToken =
                new FinApiUserAccessToken(finAPIAccessToken.getAccessToken());

        return Optional.of(finApiUserAccessToken);
    }

    public List<String> getPersonIds() {
        List<String> personIds = new ArrayList<>();

        for (Person person : personRepository.findAll()) {
            personIds.add(person.getPersonId());
        }

        return personIds;
    }

    public List<Person> getPersons() {
        List<Person> persons = new ArrayList<>();

        personRepository.findAll().forEach(persons::add);

        return persons;
    }
}
