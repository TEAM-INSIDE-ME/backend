package com.insideme.insidemebackend.dto.OpenAI;


public record messagesList(
        String role,
        Content[] content
) {
}
