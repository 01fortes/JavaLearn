package com.jsamkt.learn.solid.di.good;

public class NotificationService {
    private final MessageSender messageSender;

    // Dependency is injected
    public NotificationService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void sendNotification(String message, String recipient) {
        messageSender.sendMessage(message, recipient);
    }
}
