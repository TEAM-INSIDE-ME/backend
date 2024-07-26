package com.insideme.insidemebackend.dto.characterDto;

import com.insideme.insidemebackend.domain.Character;
import lombok.Getter;
import lombok.Setter;

import java.util.Queue;

@Getter
@Setter
public class CreateRequest {
    String userId;
    Character.CharacterState state;
    Queue<String> emotions;
}
