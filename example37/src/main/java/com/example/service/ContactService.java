package com.example.service;

import com.example.config.CustomProps;
import com.example.constants.ContactConstants;
import com.example.model.Contact;
import com.example.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ContactService {

    private final ContactRepository contactRepository;

    private final CustomProps customProps;
    /**
     * Save Contact Details into DB
     * @param contact is a model of details about message
     * @return boolean
     */
    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = false;
        contact.setStatus(ContactConstants.OPEN);
        Contact savedContact = contactRepository.save(contact);
        if(savedContact.getContactId() > 0){
            isSaved = true;
        }
        return isSaved;
    }

    public List<Contact> findMessagesWithOpenStatus() {
        return contactRepository.findByStatus(ContactConstants.OPEN);
    }
    public Page<Contact> findMessagesWithOpenStatus(int pageNum, String sortField, String sortDir) {
        int pageSize = customProps.getPageSize();
        if(null != customProps.getContact() && null != customProps.getContact().get("pageSize")){
            pageSize = Integer.parseInt(customProps.getContact().get("pageSize").trim());
        }
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sortDir.equals("asc") ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending());
        Page<Contact> msgPage = contactRepository.findByStatusWithQuery(ContactConstants.OPEN, pageable);
        return msgPage;
    }



    public boolean updateMessageStatus(int contactId) {
        boolean isUpdated = false;
        int rows = contactRepository.updateMessageStatus(ContactConstants.CLOSE, contactId);
        if(rows > 0) {
            isUpdated = true;
        }
        return isUpdated;
    }
}
