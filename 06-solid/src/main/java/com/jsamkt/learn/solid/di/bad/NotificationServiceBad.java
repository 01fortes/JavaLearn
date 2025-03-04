package com.jsamkt.learn.solid.di.bad;

import com.jsamkt.learn.solid.di.good.EmailServiceBad;

public class NotificationServiceBad {
    private EmailServiceBad emailService;

    public NotificationServiceBad() {
        // Hard dependency on EmailServiceBad
        this.emailService = new EmailServiceBad();
    }

    public void sendNotification(String message, String recipient) {
        emailService.sendEmail(message, recipient);
    }
}
