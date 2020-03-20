package com.tbsklg.springtoken.controllers;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PersonIdValidator implements ConstraintValidator<ValidPersonId, String> {

    private static final Pattern PATTERN = Pattern.compile("^\\d{1,12}$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return PATTERN.matcher(value)
                .matches();
    }

    public boolean isValid(String value) {
        return isValid(value, null);
    }
}
