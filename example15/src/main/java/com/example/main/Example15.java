package com.example.main;


import com.example.beans.Person;
import com.example.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example15 {

    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        System.out.println("Message before retrieving the Person bean from Spring Context");

        Person person = context.getBean(Person.class);

        System.out.println("Message after retrieving the Person bean from Spring Context");



    }
}