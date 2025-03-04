package com.jsamkt.learn.solid.sr;

import com.jsamkt.learn.solid.sr.bad.UserBad;
import com.jsamkt.learn.solid.sr.good.EmailServiceGood;
import com.jsamkt.learn.solid.sr.good.UserGood;
import com.jsamkt.learn.solid.sr.good.UserRepositoryGood;

public class SingleResponsibilityDemo {

    public static void demo(){
        demonstrateSingleResponsibility();
    }

    /**
     * Single Responsibility Principle: A class should have only one reason to change,
     * meaning it should have only one responsibility or job.
     */
    private static void demonstrateSingleResponsibility() {
        System.out.println("\n--- Single Responsibility Principle ---");

        demonstrateSingleResponsibilityBad();
        demonstrateSingleResponsibilityGood();

        // Benefit: each class has a single responsibility
        System.out.println("\nBenefits of SRP:");
        System.out.println("1. Easier to understand and maintain");
        System.out.println("2. Classes are more focused and cohesive");
        System.out.println("3. Changes to one responsibility don't affect others");
        System.out.println("4. Better testability");
    }

    private static void demonstrateSingleResponsibilityBad(){
        // Bad example - class with multiple responsibilities
        System.out.println("Bad example (class with multiple responsibilities):");

        UserBad userBad = new UserBad("john_doe", "john@example.com");
        userBad.saveToDatabase(); // User class handling database operations
        userBad.sendEmail("Welcome!"); // User class handling email operations
    }

    private static void demonstrateSingleResponsibilityGood(){
        // Good example - separated responsibilities
        System.out.println("\nGood example (separated responsibilities):");

        UserGood userGood = new UserGood("jane_doe", "jane@example.com");

        UserRepositoryGood userRepositoryGood = new UserRepositoryGood();
        userRepositoryGood.save(userGood);

        EmailServiceGood emailServiceGood = new EmailServiceGood();
        emailServiceGood.sendEmail(userGood.getEmail(), "Welcome!");
    }
}
