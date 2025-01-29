package com.example.musicn.controller;

import com.example.musicn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")

    public String registerUser(@RequestParam String username, @RequestParam String password, Model model) {
        System.out.println("Received registration request: " + username);
        if (userService.registerUser(username, password)) {
            model.addAttribute("message", "Registration successful!");
        } else {
            model.addAttribute("message", "Username already taken.");
        }
        return "register";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
        if (userService.authenticateUser(username, password)) {
            model.addAttribute("message", "Login successful!");
            return "welcome";
        } else {
            model.addAttribute("message", "Invalid credentials.");
            return "login";
        }
    }
}
