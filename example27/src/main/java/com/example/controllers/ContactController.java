package com.example.controllers;



import com.example.model.Contact;
import com.example.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@Controller
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(value = {"/contact"})
    public String displayContactPage(Model model) {

        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @RequestMapping(value = "/saveMsg", method = POST) // Could use @PostMapping without (method = POST)
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors) {
        if(errors.hasErrors()){
            log.error("Contact form validation failed due to: " + errors);
            return "contact"; // indicates to the Spring MVC - don't invoke the action again,
            // instead just display contact.html along with the errors if there are any available
        }
        contactService.saveMessageDetails(contact);
        return "redirect:/contact"; // invokes the contact action again from the starting.
    }

    @RequestMapping("/displayMessages")
    public ModelAndView displayMessages(Model model){
        List<Contact> contactsMessages = contactService.findMessagesWithOpenStatus();
        ModelAndView modelAndView = new ModelAndView("messages.html");
        modelAndView.addObject("contactMessages", contactsMessages);
        return modelAndView;
    }

    @RequestMapping(value = "/closeMsg", method = GET) // Could use @PostMapping without (method = POST)
    public String closeMessage(@RequestParam int id, Authentication authentication) {
        contactService.updateMessageStatus(id, authentication.getName());
        return "redirect:/displayMessages";
    }

}
