package com.insideme.insidemebackend.domain.OpenAI;

import lombok.Getter;

@Getter
public class Content {
    private final String type;
    private final Object text;

    public Content(String type, Object text) {
        this.type = type;
        this.text = text;
    }
}
