package com.insideme.insidemebackend.domain.MongoDB;

import lombok.Getter;

@Getter
public class Document {
    private final String content;
    private final String title;
    private final String font;

    public Document(String content, String title, String font) {
        this.content=content;
        this.title=title;
        this.font=font;
    }
}
