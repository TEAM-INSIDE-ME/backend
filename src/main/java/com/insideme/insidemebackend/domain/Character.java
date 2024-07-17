package com.insideme.insidemebackend.domain;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@Document(collection = "character")
public class Character {
    @Id
    private String characterId; //mongodb에서 자동으로 생성하는 id
    private String userId;
    private int state;
    private List<String> emotions;
}
