package com.insideme.insidemebackend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:MongoDB.properties")
public class MongoDBConfig {
    @Value("${mongodb.api.key}")
    private String apiKey;

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    // Getter for mongoUri
    public String getMongoUri() {
        return mongoUri;
    }
    @Bean
    public RestTemplate mongoDBrestTemplate(){
        RestTemplate mongoDBrestTemplate = new RestTemplate();
        mongoDBrestTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Content-Type", "application/json");
            request.getHeaders().add("apiKey", apiKey);
            return execution.execute(request, body);
        });
        return mongoDBrestTemplate;
    }
}