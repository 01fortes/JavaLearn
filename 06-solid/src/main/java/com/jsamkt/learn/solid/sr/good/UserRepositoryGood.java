package com.jsamkt.learn.solid.sr.good;

public class UserRepositoryGood {
    public void save(UserGood userGood) {
        System.out.println("Saving user " + userGood.getUsername() + " to database");
        // Database logic here
    }
}
