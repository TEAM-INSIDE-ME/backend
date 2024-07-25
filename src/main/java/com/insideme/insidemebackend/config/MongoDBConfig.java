package com.insideme.insidemebackend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MongoDBConfig {
    @Value("${mongodb.api.key}")
    private String apiKey;

    @Bean
    public RestTemplate mongoDBrestTemplate(){
        RestTemplate mongoDBrestTemplate = new RestTemplate();
        mongoDBrestTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("apiKey", apiKey);
            request.getHeaders().add("Accept", "application/json");
            return execution.execute(request, body);
        });
        return mongoDBrestTemplate;
    }
}