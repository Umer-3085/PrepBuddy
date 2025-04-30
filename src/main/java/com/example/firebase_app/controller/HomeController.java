package com.example.firebase_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        // Update the welcome message
        return "Welcome to the Spring Boot App!";
    }
}
