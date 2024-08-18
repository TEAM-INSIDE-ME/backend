package com.insideme.insidemebackend.dto.characterDto;

import com.insideme.insidemebackend.domain.Character;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCharacterReq {
    String userId;
    Character.CharacterState state;
    String[] emotions;
}
