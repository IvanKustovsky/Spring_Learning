package com.example.beans;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {


    private String name = "Ivan";
    private Vehicle vehicle;

    @Autowired  // It is optional ONLY if there is ONE constructor in the class, but RECOMMENDED to use always
    public Person(Vehicle vehicle){
        System.out.println("Person bean created by Spring");
        this.vehicle = vehicle;
    }

    /*
   The @Autowired annotation marks on a field, constructor, Setter method
   is used to auto-wire the beans that is ‘injecting beans'(Objects) at runtime
   by Spring Dependency Injection mechanism
    */
    /*@Autowired(required = false) eliminates NoSuchBeanDefinitionException */
    // Using @Autowired to fields and setters is NOT recommended


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }


    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void printHello(){
        System.out.println("Printing Hello from Component Person Bean");
    }

    @Override
    public String toString() {
        return "Person name is - " + name;
    }
}
