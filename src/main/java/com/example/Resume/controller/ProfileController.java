package com.example.Resume.controller;


import com.example.Resume.model.Profile;
import com.example.Resume.model.Project;
import com.example.Resume.service.ProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ProfileController {

    private final ProfileService service;

    public ProfileController(ProfileService service) {
        this.service = service;
    }

    @PostMapping("/profile")
    public Profile createProfile(@RequestBody Profile profile) {
        return service.saveProfile(profile);
    }

    @GetMapping("/profile")
    public Profile getProfile() {
        return service.getProfile();
    }

    @GetMapping("/db")
    public String dbName() {
        return service.currentDatabase();
    }

    @GetMapping("/projects")
    public List<Project> projectsBySkill(@RequestParam String skill) {
        return service.getProjectsBySkill(skill);
    }

    @PutMapping("/profile")
    public Profile updateProfile(@RequestBody Profile profile) {
        return service.updateProfile(profile);
    }

    @GetMapping("/search")
    public List<String> search(@RequestParam String q) {
        return service.search(q);
    }


}
