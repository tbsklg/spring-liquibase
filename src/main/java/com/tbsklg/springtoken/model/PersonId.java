package com.tbsklg.springtoken.model;

import com.tbsklg.springtoken.controllers.PersonIdValidator;
import com.tbsklg.springtoken.exceptions.PersonIdNotValidException;

import java.text.MessageFormat;

public class PersonId {

    private static final PersonIdValidator personIdValidator = new PersonIdValidator();

    private final String value;

    private PersonId(String value) {
        this.value = value;
    }

    public static PersonId of(String personId){
        throwsIfNotValid(personId);

        String withoutLeadingZeros = personId.replaceAll("^0+", "");

        return new PersonId(withoutLeadingZeros);
    }

    private static void throwsIfNotValid(String personId) {
        if (!personIdValidator.isValid(personId)) {
            String message = MessageFormat.format("Person Id {0} not valid", personId);
            throw new PersonIdNotValidException(message);
        }
    }

    public String getValue() {
        return value;
    }
}
