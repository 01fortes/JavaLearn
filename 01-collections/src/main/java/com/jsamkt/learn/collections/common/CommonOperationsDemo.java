package com.jsamkt.learn.collections.common;

import java.util.*;
import java.util.stream.Collectors;

public class CommonOperationsDemo {

    public static void demo(){
        demonstrateCommonOperations();
    }

    private static void demonstrateCommonOperations() {
        System.out.println("\n--- Common Collection Operations ---");

        List<Person> people = Arrays.asList(
                new Person("Alice", 28),
                new Person("Bob", 35),
                new Person("Charlie", 22),
                new Person("Alice", 40)
        );

        // Filtering
        List<Person> youngPeople = people.stream()
                .filter(p -> p.age < 30)
                .collect(Collectors.toList());
        System.out.println("Young people (age < 30): " + youngPeople);

        // Finding
        Optional<Person> alice = people.stream()
                .filter(p -> p.name.equals("Alice"))
                .findFirst();
        System.out.println("First person named Alice: " + alice.orElse(null));

        // Grouping
        Map<String, List<Person>> peopleByName = people.stream()
                .collect(Collectors.groupingBy(p -> p.name));
        System.out.println("People grouped by name: " + peopleByName);

        // Mapping to a different type
        Map<String, Integer> nameToAgeMap = people.stream()
                .collect(Collectors.toMap(
                        p -> p.name + "-" + UUID.randomUUID().toString().substring(0, 5), // Ensure uniqueness
                        p -> p.age
                ));
        System.out.println("Name to age map: " + nameToAgeMap);

        // Converting to a different collection
        Set<String> uniqueNames = people.stream()
                .map(p -> p.name)
                .collect(Collectors.toSet());
        System.out.println("Unique names: " + uniqueNames);

        // Joining
        String namesJoined = people.stream()
                .map(p -> p.name)
                .collect(Collectors.joining(", "));
        System.out.println("All names joined: " + namesJoined);

        // Sorting
        List<Person> sortedByAge = people.stream()
                .sorted(Comparator.comparingInt(p -> p.age))
                .collect(Collectors.toList());
        System.out.println("People sorted by age: " + sortedByAge);

        // Reducing - calculating sum
        int totalAge = people.stream()
                .mapToInt(p -> p.age)
                .sum();
        System.out.println("Total age of all people: " + totalAge);

        // Finding max/min
        Optional<Person> oldest = people.stream()
                .max(Comparator.comparingInt(p -> p.age));
        System.out.println("Oldest person: " + oldest.orElse(null));
    }

    // Simple class for demonstration
    static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }
}
