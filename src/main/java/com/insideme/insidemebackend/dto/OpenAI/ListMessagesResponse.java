package com.insideme.insidemebackend.dto.OpenAI;

import com.insideme.insidemebackend.domain.OpenAI.Data;

public record ListMessagesResponse(
        Data[] data

) {
}