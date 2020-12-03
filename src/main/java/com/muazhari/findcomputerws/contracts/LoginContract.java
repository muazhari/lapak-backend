package com.muazhari.findcomputerws.contracts;

public class LoginContract {
    private final String username;
    private final String password;

    public LoginContract(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
