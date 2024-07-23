package com.insideme.insidemebackend.domain.OpenAI;

import lombok.Getter;

@Getter
public class Data {
    private final String thread_id;
    private final String role;
    private final Content[] content;

    public Data(String threadId, String role, Content[] content) {
        thread_id = threadId;
        this.role = role;
        this.content = content;
    }
}
