package com.insideme.insidemebackend.dto.OpenAI;

public record CreateRunRequest(
        String assistant_id,
        String additional_instructions,
        Integer max_prompt_tokens,
        Integer max_completion_tokens,
        Object response_format
) {
}