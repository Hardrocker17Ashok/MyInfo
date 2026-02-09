package com.example.Resume.service;

import com.example.Resume.model.Profile;
import com.example.Resume.model.Project;
import com.example.Resume.repository.ProfileRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;

@Service
public class ProfileService {

    private final ProfileRepository repository;
    private final MongoTemplate mongoTemplate;

    public ProfileService(ProfileRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    public Profile saveProfile(Profile profile) {
        return repository.save(profile);
    }

    public Profile getProfile() {
        return repository.findAll().get(0);
    }

    public String currentDatabase() {
        return mongoTemplate.getDb().getName();
    }

    public List<Project> getProjectsBySkill(String skill) {
        Profile profile = getProfile();
        return profile.getProjects()
                .stream()
                .filter(p -> p.getSkills().contains(skill))
                .collect(Collectors.toList());
    }

    public Profile updateProfile(Profile updatedProfile) {
        Profile existing = repository.findAll().get(0);

        existing.setName(updatedProfile.getName());
        existing.setEmail(updatedProfile.getEmail());
        existing.setEducation(updatedProfile.getEducation());
        existing.setSkills(updatedProfile.getSkills());
        existing.setProjects(updatedProfile.getProjects());
        existing.setLinks(updatedProfile.getLinks());

        return repository.save(existing);
    }

    public List<String> search(String q) {
        Profile profile = getProfile();
        List<String> result = new ArrayList<>();
        String query = q.toLowerCase();

        if (profile.getName() != null &&
                profile.getName().toLowerCase().contains(query)) {
            result.add("Matched in name");
        }

        if (profile.getSkills() != null) {
            for (String skill : profile.getSkills()) {
                if (skill.toLowerCase().contains(query)) {
                    result.add("Matched skill: " + skill);
                }
            }
        }

        if (profile.getProjects() != null) {
            for (Project p : profile.getProjects()) {

                if (p.getTitle() != null &&
                        p.getTitle().toLowerCase().contains(query)) {
                    result.add("Matched project title: " + p.getTitle());
                }

                if (p.getDescription() != null &&
                        p.getDescription().toLowerCase().contains(query)) {
                    result.add("Matched project description: " + p.getTitle());
                }

                if (p.getSkills() != null) {
                    for (String s : p.getSkills()) {
                        if (s.toLowerCase().contains(query)) {
                            result.add("Matched project skill: " + s +
                                    " (Project: " + p.getTitle() + ")");
                        }
                    }
                }
            }
        }

        return result;
    }

    public Map<String, Object> getTopSkill() {

        List<Profile> profiles = repository.findAll();   // ✅ FIX HERE

        Map<String, Integer> skillCount = new HashMap<>();

        for (Profile profile : profiles) {

            if (profile.getSkills() != null) {

                for (String skill : profile.getSkills()) {
                    skillCount.put(skill,
                            skillCount.getOrDefault(skill, 0) + 1);
                }
            }
        }

        if (skillCount.isEmpty()) {
            return Map.of(
                    "message", "No skills found in database"
            );
        }

        String topSkill = skillCount.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();

        return Map.of(
                "topSkill", topSkill,
                "count", skillCount.get(topSkill)
        );
    }
}
