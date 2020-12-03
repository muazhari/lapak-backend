package com.muazhari.findcomputerws.models;

import java.util.UUID;

public class User {
    private final UUID id;
    private final String username;
    private final String email;
    private final String password;

    public User() {
        this.id = null;
        this.username = null;
        this.email = null;
        this.password = null;
    }

    public User(UUID id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
