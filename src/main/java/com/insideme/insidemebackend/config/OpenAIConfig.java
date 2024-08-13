package com.insideme.insidemebackend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenAIConfig {
    @Value("${openai.api.key}")
    private String apiKey;
    @Bean
    public RestTemplate openAIRestTemplate(){
        RestTemplate openAIrestTemplate = new RestTemplate();
        openAIrestTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Content-Type", "application/json");
            request.getHeaders().add("Authorization", "Bearer " + apiKey);
            request.getHeaders().add("OpenAI-Beta", "assistants=v2");
            return execution.execute(request, body);
        });
        return openAIrestTemplate;
    }
}