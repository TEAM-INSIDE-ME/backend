package com.insideme.insidemebackend.dto.OpenAI;

import java.util.List;

public record CreateRunRequest(
        String assistant_id,
        String additional_instructions,
        List<CreateMessageRequest> additional_messages,
        Integer max_prompt_tokens,
        Integer max_completion_tokens,
        Object response_format
) {
}