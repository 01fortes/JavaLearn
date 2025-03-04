package com.jsamkt.learn.concurrencyissues.deadlock;

import java.lang.management.ThreadMXBean;

public class ManagementFactory {
    public static ThreadMXBean getThreadMXBean() {
        return java.lang.management.ManagementFactory.getThreadMXBean();
    }
}
