package com.example.service;

import com.example.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j // Library for logging
@Service
public class ContactService {

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
}
