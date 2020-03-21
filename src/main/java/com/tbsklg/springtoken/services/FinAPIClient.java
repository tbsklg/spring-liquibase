package com.tbsklg.springtoken.services;

import com.tbsklg.springtoken.model.FinApiAccessToken;
import com.tbsklg.springtoken.model.FinApiUser;
import org.springframework.stereotype.Service;

@Service
public class FinAPIClient {

    public FinApiUser createPerson() {
        return FinApiUser.of(generateUsername(), generatePassword());
    }

    private String generateUsername() {
        return "username_" + Math.random();
    }

    private String generatePassword() {
        return "super_simple_password_" + Math.random();
    }

    private String generateToken() {
        return "accesstoken_" + Math.random();
    }

    public FinApiAccessToken getAccessToken() {
        return new FinApiAccessToken(generateToken());
    }
}
