package com.jsamkt.learn.solid.is.bad;

public class OldPrinter implements MachineISP {
    @Override
    public void print(String document) {
        System.out.println("Printing: " + document);
    }

    @Override
    public void fax(String document) {
        // Old printer can't fax
        System.out.println("Fax not supported");
    }

    @Override
    public void scan(String document) {
        // Old printer can't scan
        System.out.println("Scan not supported");
    }
}