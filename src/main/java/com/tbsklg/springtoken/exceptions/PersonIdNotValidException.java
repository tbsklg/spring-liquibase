package com.tbsklg.springtoken.exceptions;

public class PersonIdNotValidException extends RuntimeException {

    public PersonIdNotValidException(String message) {
        super(message);
    }
}
