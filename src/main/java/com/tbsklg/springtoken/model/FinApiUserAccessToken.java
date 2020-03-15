package com.tbsklg.springtoken.model;

import java.util.Objects;

public class FinApiUserAccessToken {

    private final String token;

    public FinApiUserAccessToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinApiUserAccessToken that = (FinApiUserAccessToken) o;
        return Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }

    @Override
    public String toString() {
        return "FinApiAccessToken{" +
                "token='" + token + '\'' +
                '}';
    }
}
