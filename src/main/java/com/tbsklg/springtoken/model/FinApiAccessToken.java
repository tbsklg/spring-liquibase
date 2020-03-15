package com.tbsklg.springtoken.model;

import java.util.Objects;

public class FinApiAccessToken {

    private final String accessToken;

    public FinApiAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinApiAccessToken that = (FinApiAccessToken) o;
        return Objects.equals(accessToken, that.accessToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessToken);
    }

    @Override
    public String toString() {
        return "FinApiAccessToken{" +
                "accessToken='" + accessToken + '\'' +
                '}';
    }
}
