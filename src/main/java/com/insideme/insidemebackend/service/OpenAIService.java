package com.insideme.insidemebackend.service;

import com.insideme.insidemebackend.dto.OpenAI.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
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

    @Qualifier("openAIrestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    public IdResponse createMessage(String threadId, CreateMessageRequest createMessageRequest) {
        String url = "https://api.openai.com/v1/threads/" + threadId + "/messages";
        return restTemplate.postForObject(url, createMessageRequest, IdResponse.class);
    }

    public IdResponse createThread(String name, Integer frequency,
                                   String gender, Date birth, String job, String purpose) {
        CreateMessageRequest createMessageRequest = new CreateMessageRequest("user",
                "안녕 내 이름은 " + name +"이고 " +birth+
                        "에 태어났어. " +gender+"이고, 직업은 "+job+
                        "이야. 나는 일기를 일주일 중" + frequency+
                        "번씩 쓰려고 해. 이 일기를 쓰는 목적은 " + purpose+"이야");
        MessagesRequest messagesRequest = new MessagesRequest(Arrays.asList(createMessageRequest));
        String url = "https://api.openai.com/v1/threads";

        return restTemplate.postForObject(url, messagesRequest, IdResponse.class);
    }

    public IdResponse createRun(String threadId, String content){
        CreateMessageRequest createMessageRequest = new CreateMessageRequest("user",content);
        List<CreateMessageRequest> createMessageRequestList = new ArrayList<>(Arrays.asList(createMessageRequest));
        Object responseFormat = new Object() {public final String type = "json_object";};
        CreateRunRequest createRunRequest = new CreateRunRequest(assistantId,"add.", createMessageRequestList,
                256,200,responseFormat);
        String url = "https://api.openai.com/v1/threads/" + threadId + "/runs";
        return restTemplate.postForObject(url, createRunRequest, IdResponse.class);
    }

    public String getAnalysis_question(String threadId, String diaryContent){
        createRun(threadId,diaryContent);
        return listMessages(threadId).data()[0].content()[0].text().value();
    }


    public ListMessagesResponse listMessages(String threadId) {
        String url = "https://api.openai.com/v1/threads/" + threadId + "/messages";
        return restTemplate.getForObject(url, ListMessagesResponse.class);
    }
}
