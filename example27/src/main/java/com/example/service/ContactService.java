package com.example.service;

import com.example.constants.ContactConstants;
import com.example.model.Contact;
import com.example.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public ContactService() {
        System.out.println("Contact Service Bean initialized");
    }

    /**
     * Save Contact Details into DB
     *
     * @param contact is a model of details about message
     * @return boolean
     */
    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = false;
        contact.setStatus(ContactConstants.OPEN);
        contact.setCreatedBy(ContactConstants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());
        int result = contactRepository.saveContactMsg(contact);
        if(result > 0){
            isSaved = true;
        }
        return isSaved;
    }

    public List<Contact> findMessagesWithOpenStatus() {
        List<Contact> contactMessages = contactRepository.findMessagesWithStatus(ContactConstants.OPEN);
        return contactMessages;
    }

    public boolean updateMessageStatus(int contactId, String updatedBy) {
        boolean isUpdated = false;
        int result = contactRepository.updateMessageStatus(contactId, ContactConstants.CLOSE, updatedBy);
        if(result > 0){
            isUpdated = true;
        }
        return isUpdated;
    }
}
