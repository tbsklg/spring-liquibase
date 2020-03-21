package com.tbsklg.springtoken.model;

import com.tbsklg.springtoken.controllers.PersonIdValidator;
import com.tbsklg.springtoken.exceptions.PersonIdNotValidException;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.text.MessageFormat;

@Embeddable
public class PersonId implements Serializable {

    private static final PersonIdValidator personIdValidator = new PersonIdValidator();

    private final String personId;

    private PersonId(String value) {
        this.personId = value;
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
        return personId;
    }
}
