package com.jsamkt.learn.concurrency.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletableFutureDemo {

    public static void demo()  {
        try {
            demonstrateCompletableFuture();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void demonstrateCompletableFuture() throws Exception {
        System.out.println("\n--- CompletableFuture ---");

        // Simple async computation
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Result from future 1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Result from future 2";
        });

        // Chain of transformations
        CompletableFuture<String> chainedFuture = future1
                .thenCombine(future2, (result1, result2) -> result1 + " & " + result2)
                .thenApply(combined -> combined.toUpperCase())
                .exceptionally(ex -> "Error: " + ex.getMessage());

        System.out.println("Final result: " + chainedFuture.get());

        // Error handling
        CompletableFuture<String> errorFuture = CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new RuntimeException("Simulated error");
            }
            return "This will not be returned";
        });

        CompletableFuture<String> recoveredFuture = errorFuture
                .exceptionally(ex -> "Recovered from: " + ex.getMessage());

        System.out.println("Recovered result: " + recoveredFuture.get());

        // CompletableFuture with timeout
        CompletableFuture<String> timeoutFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Result after long operation";
        });

        try {
            String result = timeoutFuture.get(1, TimeUnit.SECONDS);
            System.out.println("Result with timeout: " + result);
        } catch (TimeoutException e) {
            System.out.println("Operation timed out!");
        }
    }
}
