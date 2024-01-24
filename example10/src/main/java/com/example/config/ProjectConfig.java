package com.example.config;

import com.example.beans.Person;
import com.example.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
Spring @Configuration annotation is part of the spring core framework.
Spring Configuration annotation indicates that the class has @Bean definition
methods. So Spring container can process the class and generate Spring Beans
to be used in the application.
* */
@Configuration
public class ProjectConfig {

    @Bean
    public Vehicle vehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setName("Toyota");
        return vehicle;
    }

    /*
   Here in the below code, we are trying to auto-wire or establish a relationship between Person and
   Vehicle, by passing the 'vehicle' bean as a parameter to the 'person' bean creation method.
   This way, the Spring IoC container will automatically inject the 'Vehicle' instance (created by
   the 'vehicle' method) into the 'Person' bean during its initialization.
   */
    @Bean
    public Person person(Vehicle vehicle) {  // auto-wiring process
        Person person = new Person();
        person.setName("Lucy");
        person.setVehicle(vehicle);
        return person;
    }

}