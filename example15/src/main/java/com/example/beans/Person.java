package com.example.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component(value = "personBean")
@Lazy
public class Person {
    // The @Lazy annotation indicates that the initialization of this bean should be deferred until it is actually needed.
    // This can be beneficial for improving application startup time and resource utilization.
    // Uses in very rare scenarios. Default value is Eager - initialization during startup of app
    // Problem of @Lazy when exception happens during Bean creation user will get runtime exception

    private String name = "Ivan";
    private final Vehicle vehicle;

    @Autowired
    public Person(Vehicle vehicle){
        System.out.println("Person bean was created by Spring");
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
