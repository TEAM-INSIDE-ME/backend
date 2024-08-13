package com.insideme.insidemebackend.controller;

import com.insideme.insidemebackend.dto.OpenAI.*;
import com.insideme.insidemebackend.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/openai")
@RequiredArgsConstructor // auto DI ,final 객체만 DI
public class OpenAIController {

    private final OpenAIService openAIService;

    @PostMapping("/createMessage/{thread_id}")
    public ResponseEntity<IdResponse> createMessage(@PathVariable("thread_id") String thread_id, @RequestBody CreateMessageRequest createMessageRequest) {
        IdResponse idResponse = openAIService.createMessage(thread_id, createMessageRequest);
        return ResponseEntity.ok(idResponse);
    }

    @GetMapping("/listMessages/{thread_id}")
    public ResponseEntity<ListMessagesResponse> listMessages(@PathVariable("thread_id") String thread_id) {
        ListMessagesResponse listMessagesResponse = openAIService.listMessages(thread_id);
        return ResponseEntity.ok(listMessagesResponse);
    }

}
