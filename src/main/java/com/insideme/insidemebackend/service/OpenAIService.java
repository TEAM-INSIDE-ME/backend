package com.insideme.insidemebackend.service;

import com.insideme.insidemebackend.dto.OpenAI.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenAIService {

    @Qualifier("openAIrestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    public IdResponse createMessage(String threadId, CreateMessageRequest createMessageRequest) {
        String url = "https://api.openai.com/v1/threads/" + threadId + "/messages";
        return restTemplate.postForObject(url, createMessageRequest, IdResponse.class);
    }

    public IdResponse createRun(String threadId, CreateRunRequest createRunRequest) {
        String url = "https://api.openai.com/v1/threads/" + threadId + "/runs";
        return restTemplate.postForObject(url, createRunRequest, IdResponse.class);
    }

    public IdResponse createThread(CreateThreadRequest createThreadRequest) {
        String url = "https://api.openai.com/v1/threads";
        return restTemplate.postForObject(url, createThreadRequest, IdResponse.class);
    }

    public ListMessagesResponse listMessages(String threadId) {
        String url = "https://api.openai.com/v1/threads/" + threadId + "/messages";
        return restTemplate.getForObject(url, ListMessagesResponse.class);
    }
}
