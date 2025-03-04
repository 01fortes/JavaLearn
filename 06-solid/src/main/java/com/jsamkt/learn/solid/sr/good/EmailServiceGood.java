package com.jsamkt.learn.solid.sr.good;

public class EmailServiceGood {
    public void sendEmail(String recipient, String message) {
        System.out.println("Sending email to " + recipient + ": " + message);
        // Email sending logic here
    }
}
