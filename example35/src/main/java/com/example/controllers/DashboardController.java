package com.example.controllers;


import com.example.model.Person;
import com.example.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;

@Controller
@Slf4j
public class DashboardController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping("/dashboard")
    public String displayDashboardPage(Model model, Authentication authentication, HttpSession session) {
        // authentication.getName() return email of auth user, because inside UsernamePasswordAuthPr class
        // we pass email as first par
        Person person = personRepository.readByEmail(authentication.getName());
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        if(person.getEazyClass() != null && person.getEazyClass().getName() != null){
            model.addAttribute("enrolledClass", person.getEazyClass().getName());
        }
        session.setAttribute("loggedInPerson", person);
        logMessages();
        return "dashboard";
    }

    private void logMessages() {
        log.error("Error message from Dashboard page");
        log.warn("Warn message from Dashboard page");
        log.info("Info message from Dashboard page");
        log.debug("Debug message from Dashboard page");
        log.trace("Trace message from Dashboard page");
    }
}
