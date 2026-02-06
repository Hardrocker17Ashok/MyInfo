package com.example.Resume.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@RestController
public class HealthController {

    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", "UP");
        map.put("service", "Me-API Playground");
        map.put("timestamp", System.currentTimeMillis());
        return map;
    }
}
