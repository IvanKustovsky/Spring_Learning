package com.example.controllers;


import com.example.model.Person;
import com.example.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;

@Controller
@Slf4j
@RequiredArgsConstructor
public class DashboardController {

    private final PersonRepository personRepository;

    @Value("${property.pageSize}")
    private int defaultPageSize;
    @Value("${property.contact.successMsg}")
    private String message;

    private final Environment environment;

    @RequestMapping("/dashboard")
    public String displayDashboardPage(Model model, Authentication authentication, HttpSession session) {
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

        log.error("defaultPageSize value with @Value annotation is " + defaultPageSize);
        log.warn("successMsg value with @Value annotation is " + message);

        log.error("defaultPageSize value with Environment is :" + environment.getProperty("property.pageSize"));
        log.error("successMsg value with Environment is :" + environment.getProperty("property.contact.successMsg"));
        log.error("Java home environment variable using Environment is :" + environment.getProperty("JAVA_HOME"));
    }

}
