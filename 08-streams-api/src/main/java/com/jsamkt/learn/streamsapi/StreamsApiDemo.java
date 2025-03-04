package com.jsamkt.learn.streamsapi;

import com.jsamkt.learn.streamsapi.basics.StreamBasicsDemo;
import com.jsamkt.learn.streamsapi.operations.IntermediateOperationsDemo;
import com.jsamkt.learn.streamsapi.operations.TerminalOperationsDemo;
import com.jsamkt.learn.streamsapi.advanced.AdvancedStreamsDemo;
import com.jsamkt.learn.streamsapi.collectors.CollectorsDemo;
import com.jsamkt.learn.streamsapi.parallel.ParallelStreamsDemo;
import com.jsamkt.learn.streamsapi.practical.PracticalExamplesDemo;

/**
 * Main demonstration class for Java Streams API.
 * The Streams API provides a functional approach to processing collections of objects.
 * It allows operations on collections of data in a declarative way.
 */
public class StreamsApiDemo {
    
    public static void main(String[] args) {
        System.out.println("===== STREAMS API DEMONSTRATION =====");
        
        // Demonstrate basic stream concepts
        StreamBasicsDemo.demo();
        
        // Demonstrate intermediate operations
        IntermediateOperationsDemo.demo();
        
        // Demonstrate terminal operations
        TerminalOperationsDemo.demo();
        
        // Demonstrate collectors
        CollectorsDemo.demo();
        
        // Demonstrate advanced stream operations
        AdvancedStreamsDemo.demo();
        
        // Demonstrate parallel streams
        ParallelStreamsDemo.demo();
        
        // Demonstrate practical examples
        PracticalExamplesDemo.demo();
        
        System.out.println("===== END OF STREAMS API DEMONSTRATION =====");
    }
}