package com.example.beans;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Person {


    private String name = "Ivan";
    private final Vehicle vehicle;

    @Autowired
    public Person(@Qualifier("vehicle1") Vehicle vehicle){
        System.out.println("Person bean created by Spring");
        this.vehicle = vehicle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public String toString() {
        return "Person name is - " + name;
    }
}
