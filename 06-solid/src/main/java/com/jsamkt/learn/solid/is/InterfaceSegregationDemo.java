package com.jsamkt.learn.solid.is;

import com.jsamkt.learn.solid.is.bad.MultiFunctionPrinter;
import com.jsamkt.learn.solid.is.bad.OldPrinter;
import com.jsamkt.learn.solid.is.good.BasicPrinter;
import com.jsamkt.learn.solid.is.good.ModernPrinter;

public class InterfaceSegregationDemo {

    public static void demo(){
        demonstrateInterfaceSegregation();
    }

    /**
     * Interface Segregation Principle: Clients should not be forced to depend on interfaces
     * they do not use. Many client-specific interfaces are better than one general-purpose interface.
     */
    private static void demonstrateInterfaceSegregation() {
        System.out.println("\n--- Interface Segregation Principle ---");

        demonstrateInterfaceSegregationBad();
        demonstrateInterfaceSegregationGood();

        // Benefits of following ISP
        System.out.println("\nBenefits of ISP:");
        System.out.println("1. Prevents classes from depending on methods they don't use");
        System.out.println("2. Leads to more focused and cohesive interfaces");
        System.out.println("3. Reduces impact of changes to interfaces");
        System.out.println("4. Increases flexibility and reusability");
    }

    private static void demonstrateInterfaceSegregationBad(){
        // Bad example - violates ISP
        System.out.println("Bad example (violates ISP):");

        MultiFunctionPrinter mfp = new MultiFunctionPrinter();
        mfp.print("Document");
        mfp.fax("Document");
        mfp.scan("Document");

        OldPrinter oldPrinter = new OldPrinter();
        oldPrinter.print("Document");
        // These methods are not applicable but OldPrinter has to implement them
        oldPrinter.fax("Document"); // Does nothing
        oldPrinter.scan("Document"); // Does nothing
    }

    private static void demonstrateInterfaceSegregationGood(){
        // Good example - follows ISP
        System.out.println("\nGood example (follows ISP):");

        ModernPrinter modernPrinter = new ModernPrinter();
        modernPrinter.print("Document");
        modernPrinter.fax("Document");
        modernPrinter.scan("Document");

        BasicPrinter basicPrinter = new BasicPrinter();
        basicPrinter.print("Document");
        // BasicPrinter doesn't need to implement fax or scan
    }
}
