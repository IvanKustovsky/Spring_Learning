package com.example.controllers;


import com.example.model.Person;
import com.example.service.PersonService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
@RequestMapping("public")
public class PublicController {

    @Autowired
    PersonService personService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String displayRegisterPage(Model model){
        model.addAttribute("person", new Person());
        return "register";
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String displayRegisterPage(@Valid @ModelAttribute("person") Person person, Errors errors){
        if(errors.hasErrors()){
            log.error("Person form validation failed due to: " + errors);
            return "register";
        }
        //personService.savePerson(person);
        return "redirect:/login?register=true";
    }
}
