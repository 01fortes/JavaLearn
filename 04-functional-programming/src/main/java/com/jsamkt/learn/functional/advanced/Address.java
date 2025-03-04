package com.jsamkt.learn.functional.advanced;


public class Address {
    private final String street;
    private final City city;

    public Address(String street, City city) {
        this.street = street;
        this.city = city;
    }

    public String getStreet() { return street; }
    public City getCity() { return city; }
}
