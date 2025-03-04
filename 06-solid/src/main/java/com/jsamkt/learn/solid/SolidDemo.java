package com.jsamkt.learn.solid;

import com.jsamkt.learn.solid.di.DependencyInversionDemo;
import com.jsamkt.learn.solid.is.InterfaceSegregationDemo;
import com.jsamkt.learn.solid.lsp.LSPDemo;
import com.jsamkt.learn.solid.oc.OpenClosedDemo;
import com.jsamkt.learn.solid.sr.SingleResponsibilityDemo;

/**
 * This tutorial demonstrates SOLID principles in Java.
 */
public class SolidDemo {

    public static void main(String[] args) {
        System.out.println("===== Java SOLID Principles Tutorial =====");

        SingleResponsibilityDemo.demo();
        OpenClosedDemo.demo();
        LSPDemo.demo();
        InterfaceSegregationDemo.demo();
        DependencyInversionDemo.demo();
    }
}
