package com.insideme.insidemebackend.dto.OpenAI;

public record CreateMessageRequest(
        String role,
        String content
) {
}