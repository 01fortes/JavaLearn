package com.jsamkt.learn.functional.advanced;


public class User {
    private final String email;
    private final Address address;

    public User(String email, Address address) {
        this.email = email;
        this.address = address;
    }

    public String getEmail() { return email; }
    public Address getAddress() { return address; }
}
