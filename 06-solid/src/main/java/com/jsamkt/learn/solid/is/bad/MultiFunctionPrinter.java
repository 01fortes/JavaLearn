package com.jsamkt.learn.solid.is.bad;

public class MultiFunctionPrinter implements MachineISP {
    @Override
    public void print(String document) {
        System.out.println("Printing: " + document);
    }

    @Override
    public void fax(String document) {
        System.out.println("Faxing: " + document);
    }

    @Override
    public void scan(String document) {
        System.out.println("Scanning: " + document);
    }
}
