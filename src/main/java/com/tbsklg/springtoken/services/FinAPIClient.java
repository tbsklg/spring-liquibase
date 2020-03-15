package com.tbsklg.springtoken.services;

import com.tbsklg.springtoken.model.FinApiAccessToken;
import com.tbsklg.springtoken.model.Person;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FinAPIClient {

    public Person createPerson(String personId) {
        return Person.from(personId, generateUsername(personId), generatePassword(personId));
    }

    private String generateUsername(String personId) {
        String username = "username_" + personId;
        return String.valueOf(username.hashCode());
    }

    private String generatePassword(String personId) {
        String password = "super_simple_password_" + personId;
        return String.valueOf(password.hashCode());
    }

    private String generateToken(String personId) {
        Random r = new Random();
        return String.valueOf((r.nextInt(Integer.parseInt(personId)) + 'a'));
    }

    public FinApiAccessToken getAccessToken(Person person) {
        return new FinApiAccessToken(generateUsername(generateToken(person.getPersonId())));
    }
}
