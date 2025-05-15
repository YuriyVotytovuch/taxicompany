package com.example.taxicompany.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class AuthController {
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
        if (error != null) {
            model.addAttribute("error", "Неправильна електронна пошта або пароль");
        }
        if (logout != null) {
            model.addAttribute("error", "Ви успішно вийшли з системи");
        }
        return "login";
    }

    @GetMapping("/")
    public String enter() {
        return "redirect:/login";
    }
}