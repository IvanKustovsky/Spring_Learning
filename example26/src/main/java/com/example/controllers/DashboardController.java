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

        return "dashboard";
    }
}
