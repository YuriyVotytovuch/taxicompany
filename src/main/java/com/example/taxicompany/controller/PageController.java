package com.example.taxicompany.controller;

import com.example.taxicompany.entity.User;
import com.example.taxicompany.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home() {
        return "main";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/services")
    public String services() {
        return "services";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
//    @GetMapping("/reg")
//    public String reg() {
//        return "reg";
//    }
}