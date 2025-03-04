package com.jsamkt.learn.solid.di;


import com.jsamkt.learn.solid.di.bad.NotificationServiceBad;
import com.jsamkt.learn.solid.di.good.EmailSender;
import com.jsamkt.learn.solid.di.good.MessageSender;
import com.jsamkt.learn.solid.di.good.NotificationService;
import com.jsamkt.learn.solid.di.good.SMSSender;

public class DependencyInversionDemo {

    public static void demo(){
        demonstrateDependencyInversion();
    }

    /**
     * Dependency Inversion Principle: High-level modules should not depend on low-level modules.
     * Both should depend on abstractions. Abstractions should not depend on details.
     * Details should depend on abstractions.
     */
    private static void demonstrateDependencyInversion() {
        System.out.println("\n--- Dependency Inversion Principle ---");

        demonstrateDependencyInversionBad();
        demonstrateDependencyInversionGood();

        // Benefits of following DIP
        System.out.println("\nBenefits of DIP:");
        System.out.println("1. Reduces coupling between high-level and low-level modules");
        System.out.println("2. Makes system more flexible and adaptable to changes");
        System.out.println("3. Facilitates testing through dependency injection");
        System.out.println("4. Promotes modular design and reusability");
    }

    private static void demonstrateDependencyInversionBad(){
        // Bad example - violates DIP
        System.out.println("Bad example (violates DIP):");

        NotificationServiceBad notificationBad = new NotificationServiceBad();
        notificationBad.sendNotification("Hello!", "user@example.com");
    }

    private static void demonstrateDependencyInversionGood(){
        // Good example - follows DIP
        System.out.println("\nGood example (follows DIP):");

        MessageSender emailSender = new EmailSender();
        NotificationService emailNotification = new NotificationService(emailSender);
        emailNotification.sendNotification("Hello via email!", "user@example.com");

        MessageSender smsSender = new SMSSender();
        NotificationService smsNotification = new NotificationService(smsSender);
        smsNotification.sendNotification("Hello via SMS!", "1234567890");
    }
}
