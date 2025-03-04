package com.jsamkt.learn.solid.sr.bad;

public class UserBad {
    private String username;
    private String email;

    public UserBad(String username, String email) {
        this.username = username;
        this.email = email;
    }

    // User responsibility
    public String getUsername() { return username; }
    public String getEmail() { return email; }

    // Database responsibility (should be in a separate class)
    public void saveToDatabase() {
        System.out.println("Saving user " + username + " to database");
        // Database logic here
    }

    // Email responsibility (should be in a separate class)
    public void sendEmail(String message) {
        System.out.println("Sending email to " + email + ": " + message);
        // Email sending logic here
    }
}
