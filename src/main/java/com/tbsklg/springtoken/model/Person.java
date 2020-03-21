package com.tbsklg.springtoken.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person {

    @Id
    private String personId;
    private String username;
    private String password;

    public Person() {
    }

    private Person(String personId, String username, String password) {
        this.personId = personId;
        this.username = username;
        this.password = password;
    }

    public static Person from(PersonId personId, String username, String password) {
        return new Person(personId.getValue(), username, password);
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
