package com.jsamkt.learn.solid.sr.good;

public class UserGood {
    private String username;
    private String email;

    public UserGood(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() { return username; }
    public String getEmail() { return email; }
}
