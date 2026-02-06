package com.example.Resume.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoConfig {

    private static final String CONNECTION_STRING =
            "mongodb+srv://resume17:orjhfipabyvX8QQO@cluster0.bykqcxp.mongodb.net/";

    private static final String DATABASE_NAME = "meapi";

    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory() {
        return new SimpleMongoClientDatabaseFactory(CONNECTION_STRING + DATABASE_NAME);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDatabaseFactory());
    }
}
