package com.aushev.plantstore;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@RequestMapping(path = "/")
public class MainController {

    @GetMapping(path = "/index")
    public String doGet() {
        return "index";
    }

    @PostMapping(path = "/index")
    public String doPost() {
        return "index";
    }

    @GetMapping(path = "/login")
    public String login(Model model, String error, String logout) {

        if (Objects.nonNull(error)) {
            model.addAttribute("error", "Your username and password is invalid.");
        }
        if (Objects.nonNull(logout)){
            model.addAttribute("message", "You have been logged out successfully");
        }
        return "login";
    }
}
