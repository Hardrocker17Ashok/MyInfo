package com.example.Resume.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        String uri = System.getenv("MONGODB_URI");

        if (uri == null || uri.isEmpty()) {
            throw new RuntimeException("MONGODB_URI environment variable not set!");
        }

        return MongoClients.create(uri);
    }
}
