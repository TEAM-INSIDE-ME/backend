package com.insideme.insidemebackend.controller;

import com.insideme.insidemebackend.dto.OpenAI.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
@RestController
@RequestMapping("/api/openai")
public class OpenAIController {
    @Qualifier("openAIrestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    //Create Message
    @PostMapping("/createMessage/{thread_id}")
    public ResponseEntity<IdResponse> createMessage(@PathVariable("thread_id") String thread_id, @RequestBody CreateMessageRequest createMessageRequest) {
        String url = "https://api.openai.com/v1/threads/" + thread_id + "/messages";
        IdResponse idResponse = restTemplate.postForObject(url, createMessageRequest, IdResponse.class);

        return ResponseEntity.ok(idResponse);
    }

    //Create Run
    @PostMapping("/createRun/{thread_id}")
    public ResponseEntity<IdResponse> createRun(@PathVariable("thread_id") String thread_id, @RequestBody CreateRunRequest createRunRequest) {
        String url = "https://api.openai.com/v1/threads/" + thread_id + "/runs";
        IdResponse idResponse = restTemplate.postForObject(url, createRunRequest, IdResponse.class);

        return ResponseEntity.ok(idResponse);
    }

    //Create Thread
    @PostMapping("/createThread")
    public ResponseEntity<IdResponse> createThread(@RequestBody CreateThreadRequest createThreadRequest) {
        String url = "https://api.openai.com/v1/threads";
        IdResponse idResponse = restTemplate.postForObject(url, createThreadRequest, IdResponse.class);

        return ResponseEntity.ok(idResponse);
    }

    //List Messages
    @GetMapping("/listMessages/{thread_id}")
    public ResponseEntity<ListMessagesResponse> listMessages(@PathVariable("thread_id") String thread_id) {
        String url = "https://api.openai.com/v1/threads/" + thread_id + "/messages";
        ListMessagesResponse listMessagesResponse = restTemplate.getForObject(url, ListMessagesResponse.class);

        return ResponseEntity.ok(listMessagesResponse);
    }

}
