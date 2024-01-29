package com.example.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;

@Controller
public class DashboardController {


    @RequestMapping("/dashboard")
    public String displayDashboardPage(Model model, Authentication authentication) {

        model.addAttribute("username", authentication.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        throw new RuntimeException("It's been a bad day!!!"); // added to show demo of @ExceptionHandler
        //return "dashboard";
    }
}
