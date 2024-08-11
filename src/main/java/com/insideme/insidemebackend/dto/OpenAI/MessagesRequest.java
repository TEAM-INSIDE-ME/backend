package com.insideme.insidemebackend.dto.OpenAI;

import java.util.List;

public record MessagesRequest(
        List<CreateMessageRequest> messages
) {
}
