package com.example.controllers;



import com.example.model.Contact;
import com.example.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;




import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(value = {"/contact"})
    public String displayContactPage() {
        return "contact";
    }


    /*@RequestMapping(value = "/saveMsg", method = POST)
    public ModelAndView saveMessage(@RequestParam(name = "name") String nameValue, @RequestParam String mobileNum,
                                    @RequestParam String email, @RequestParam String subject,
                                    @RequestParam String message){
        // variable name should match with html titles or use like this @RequestParam(name = "name")
        log.info("Name: " + nameValue);
        log.info("Mobile Number: " + mobileNum);
        log.info("Email: " + email);
        log.info("Subject: " + subject);
        log.info("Message: " + message);
        return new ModelAndView("redirect:/contact");
    }*/

    @RequestMapping(value = "/saveMsg", method = POST) // Could use @PostMapping without (method = POST)
    public ModelAndView saveMessage(Contact contact) {
        contactService.saveMessageDetails(contact);
        return new ModelAndView("redirect:/contact");
    }

}
