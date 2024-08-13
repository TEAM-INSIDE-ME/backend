package com.insideme.insidemebackend.service;

import com.insideme.insidemebackend.dto.OpenAI.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class OpenAIService {

    @Value("${openai.assistant.id}")
    private String assistantId;

    @Qualifier("openAIRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    public IdResponse createMessage(String threadId, CreateMessageRequest createMessageRequest) {
        String url = "https://api.openai.com/v1/threads/" + threadId + "/messages";
        return restTemplate.postForObject(url, createMessageRequest, IdResponse.class);
    }

    public IdResponse createThread() {
        String url = "https://api.openai.com/v1/threads";
        return restTemplate.postForObject(url,null, IdResponse.class);
    }

    private IdResponse createRun(String threadId, String content,String additionalInstructions){
        CreateMessageRequest createMessageRequest = new CreateMessageRequest("user",content);
        List<CreateMessageRequest> createMessageRequestList = new ArrayList<>(Arrays.asList(createMessageRequest));
        Object responseFormat = new Object() {public final String type = "json_object";};
        CreateRunRequest createRunRequest = new CreateRunRequest(assistantId,additionalInstructions, createMessageRequestList,
                256,200,responseFormat);
        String url = "https://api.openai.com/v1/threads/" + threadId + "/runs";
        return restTemplate.postForObject(url, createRunRequest, IdResponse.class);
    }

    public String getAMessageFromEmotionBot(String threadId, String msg,String additionalInstructions) {
        createRun(threadId, msg, additionalInstructions).id();

        String value = checkMessage(threadId);
        while ("".equals(value)){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread was interrupted", e);
            }
            value = checkMessage(threadId);
        }

        return value;
    }

    public String checkMessage(String threadId) {
        ListMessagesResponse response = listMessages(threadId);

        if (response.data() != null && response.data().length > 0) {
            messagesList firstMessage = response.data()[0];

            if (firstMessage.role().equals("assistant") && firstMessage.content() != null && firstMessage.content().length > 0) {
                return firstMessage.content()[0].text().value();
            }
        }
        return "";
    }

    public ListMessagesResponse listMessages(String threadId) {
        String url = "https://api.openai.com/v1/threads/" + threadId + "/messages";
        return restTemplate.getForObject(url, ListMessagesResponse.class);
    }
}
