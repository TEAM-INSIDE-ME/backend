package com.insideme.insidemebackend.dto.OpenAI;

public record ListMessagesResponse(
        messagesList[] data

) {
}