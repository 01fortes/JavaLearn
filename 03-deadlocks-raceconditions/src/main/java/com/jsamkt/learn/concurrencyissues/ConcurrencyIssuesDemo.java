package com.jsamkt.learn.concurrencyissues;


import com.jsamkt.learn.concurrencyissues.deadlock.DeadLockDemo;
import com.jsamkt.learn.concurrencyissues.livelock.LiveLockDemo;
import com.jsamkt.learn.concurrencyissues.racecondition.RaceConditionDemo;
import com.jsamkt.learn.concurrencyissues.resourceexhaustion.ResourceExhaustionDemo;
import com.jsamkt.learn.concurrencyissues.starvation.StarvationDemo;
import com.jsamkt.learn.concurrencyissues.threadleak.ThreadLeakDemo;

public class ConcurrencyIssuesDemo {
    
    public static void main(String[] args) throws Exception {
        System.out.println("===== Java Concurrency Issues Tutorial =====");

        RaceConditionDemo.demo();
        DeadLockDemo.demo();
        LiveLockDemo.demo();
        ResourceExhaustionDemo.demo();
        StarvationDemo.demo();
        ThreadLeakDemo.demo();
    }

}