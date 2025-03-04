package com.jsamkt.learn.solid.di.good;

public class EmailServiceBad {
    public void sendEmail(String message, String recipient) {
        System.out.println("Sending email to " + recipient + ": " + message);
    }
}
