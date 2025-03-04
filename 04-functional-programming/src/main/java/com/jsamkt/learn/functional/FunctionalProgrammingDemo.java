package com.jsamkt.learn.functional;

import com.jsamkt.learn.functional.advanced.AdvancedTechniqueDemo;
import com.jsamkt.learn.functional.functioncomposition.FunctionCompositionDemo;
import com.jsamkt.learn.functional.functioonalinterface.FunctionalInterfaceDemo;
import com.jsamkt.learn.functional.higherorderfunction.HigherOrderFunctionDemo;
import com.jsamkt.learn.functional.immutability.ImmutabilityDemo;
import com.jsamkt.learn.functional.methodreference.MethodReferenceDemo;
import com.jsamkt.learn.functional.practical.PracticalApplicationDemo;
import com.jsamkt.learn.functional.purefunction.PureFunctionDemo;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This tutorial demonstrates functional programming concepts in Java.
 */
public class FunctionalProgrammingDemo {

    public static void main(String[] args) {
        System.out.println("===== Java Functional Programming Tutorial =====");

        FunctionalInterfaceDemo.demo();
        MethodReferenceDemo.demo();
        FunctionCompositionDemo.demo();
        PureFunctionDemo.demo();
        HigherOrderFunctionDemo.demo();
        ImmutabilityDemo.demo();
        AdvancedTechniqueDemo.demo();
        PracticalApplicationDemo.demo();
    }

}
