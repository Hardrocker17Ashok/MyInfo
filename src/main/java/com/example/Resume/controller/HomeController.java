package com.example.Resume.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> home() {

        Map<String, Object> response = new LinkedHashMap<>();

        response.put("message", "Welcome to Me-API Playground 🚀");
        response.put("owner", "Ashok Sharma");
        response.put("role", "Backend Developer | MCA Student");
        response.put("status", "API is running successfully ✅");
        response.put("timestamp", LocalDateTime.now());

        response.put("available_endpoints", new String[] {
                "/health",
                "/api/profile",
                "/api/projects?skill=Java",
                "/api/skills/top",
                "/api/search?q=java"
        });

        response.put("note",
                "This API is built using Spring Boot and MongoDB and deployed on Render.");

        return response;
    }
}
