package com.tbsklg.springtoken.model;

import java.util.Objects;

public class FinApiUser {
    private final String username;
    private final String password;

    private FinApiUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static FinApiUser of(String username, String password) {
        return new FinApiUser(username, password);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinApiUser that = (FinApiUser) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
