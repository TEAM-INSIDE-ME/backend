package com.insideme.insidemebackend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


@Data
@Document(collection = "character")
public class Character {

    public enum CharacterState {FIRST, SECOND, THIRD, FOURTH}

    @Id
    private String characterId; //mongodb에서 자동으로 생성하는 id
    private String userId;
    private CharacterState state;
    private List<String> emotions;

    public Character() {
        this.emotions = new LinkedList<>();
    }

    public Character(String characterId, String userId, CharacterState state, List<String> emotions) {
        this.characterId = characterId;
        this.userId = userId;
        this.state = state;
        this.emotions = (emotions != null) ? emotions : new LinkedList<>();
    }
}
