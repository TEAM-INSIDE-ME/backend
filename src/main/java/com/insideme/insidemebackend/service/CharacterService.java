package com.insideme.insidemebackend.service;

import com.insideme.insidemebackend.domain.Character;
import com.insideme.insidemebackend.dto.characterDto.UpdateCharacterReq;
import com.insideme.insidemebackend.repository.CharacterMapper;
import com.insideme.insidemebackend.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private Character.CharacterState state;

    public Character initCharacter(){
        String[] emptyList = new String[7];
        Character character = new Character(null, null, Character.CharacterState.FIRST, emptyList);
        return character;
    }
    public Character saveCharacter(Character character) {
        return characterRepository.save(character);
    }

    public Optional<Character> getCharacterById(String id) {
        return characterRepository.findByUserId(id);
    }

    public Character updateCharacter(String userId, boolean achievement) {
        Optional<Character> character = getCharacterById(userId);
        UpdateCharacterReq dto = characterMapper.characterToUpdateCharacterReq(character);
        if(achievement){
            characterGrowth(dto);
        }
        else characterDegrowth(dto);
    }

    public UpdateCharacterReq characterGrowth(UpdateCharacterReq dto){
        Character.CharacterState currentState = dto.getState();
        int currentOrdinal = currentState.ordinal();
        if(currentOrdinal < Character.CharacterState.values().length - 1){
            dto.setState(state.values()[currentOrdinal + 1]);
        }
        return dto;
    }
    public UpdateCharacterReq characterDegrowth(UpdateCharacterReq dto){
        Character.CharacterState currentState = dto.getState();
        int currentOrdinal = currentState.ordinal();
        if(currentOrdinal > 0){
            dto.setState(state.values()[currentOrdinal -1]);
        }
        return dto;
    }
}
