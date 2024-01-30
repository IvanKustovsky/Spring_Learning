package com.example.service;

import com.example.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

/*
@Slf4j, is a Lombok-provided annotation that will automatically generate an SLF4J
Logger static property in the class at compilation time.
* */
@Slf4j
@Service
// @RequestScope
/*Spring will create a new instance of the bean for each HTTP request.
This means that each time a new request is made, a new instance of the bean will be created,
and it will be available only for the duration of that specific request.*/

// @SessionScope
/*Spring will create and manage a single instance of the bean per HTTP session.
The bean will be available as long as the user's session is active, persisting across multiple requests.
This allows for maintaining stateful information specific to a user throughout their session.*/

@ApplicationScope
/*Spring will make sure there is only one Bean instance created and available for a single servlet context inside
the web application.This means that the bean is shared among all users and sessions in the entire web application,
providing a global and shared state.*/

public class ContactService {

    private int counter;

    public ContactService() {
        System.out.println("Contact Service Bean initialized");
    }

    /**
     * Save Contact Details into DB
     * @param contact
     * @return boolean
     */
    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = true;
        //TODO - Need to persist data into the db table
        log.info(contact.toString());
        return isSaved;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
