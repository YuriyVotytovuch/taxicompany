package com.example.taxicompany.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home() {
        return "Main";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/main")
    public String main() {
        return "Main";
    }

    @GetMapping("/services")
    public String services() {
        return "services";
    }

    @GetMapping("/contact")
    public String contact() {
        return "Contact";
    }

    @GetMapping("/register")
    public String register() {
        return "reg";
    }
}