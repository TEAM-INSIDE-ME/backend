package com.insideme.insidemebackend.service;

import com.insideme.insidemebackend.domain.Character;
import com.insideme.insidemebackend.dto.characterDto.UpdateCharacterReq;
import com.insideme.insidemebackend.repository.CharacterMapper;
import com.insideme.insidemebackend.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.Optional;


@RequiredArgsConstructor
@Service
@Slf4j
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private Character.CharacterState state;

    public Character initCharacter(){
        String[] emptyList = new String[7];
        Character character = new Character(null, null, Character.CharacterState.FIRST, emptyList);
        return character;
    }

    @Transactional
    public Character saveCharacter(Character character) {
        return characterRepository.save(character);
    }

    /*
    public Optional<Character> getCharacterById(String id) {
        return characterRepository.findByUserId(id);
    }*/

    public Character getCharacterById(String id){
        return characterRepository.findByUserId(id);
    }

    @Transactional
    public Optional<Character> updateCharacter(String userId, boolean achievement) {
        Character character = getCharacterById(userId);
        UpdateCharacterReq dto = characterMapper.characterToUpdateCharacterReq(character);

        UpdateCharacterReq updatedDto;

        if(achievement){
            updatedDto = characterGrowth(dto);
        }
        else updatedDto = characterDegrowth(dto);
        characterMapper.updateCharacterFromDto(updatedDto, character);
        Character saved = characterRepository.save(character);
        return Optional.of(saved);
    }

    //캐릭터 성장
    public UpdateCharacterReq characterGrowth(UpdateCharacterReq dto){
        Character.CharacterState currentState = dto.getState();
        int currentOrdinal = currentState.ordinal();
        //state 가 THIRD 이하일 때 증가
        if(currentOrdinal < Character.CharacterState.values().length - 1){
            dto.setState(state.values()[currentOrdinal + 1]);
        }
        return dto;
    }

    //캐릭터 역성장
    public UpdateCharacterReq characterDegrowth(UpdateCharacterReq dto){
        Character.CharacterState currentState = dto.getState();
        int currentOrdinal = currentState.ordinal();
        //state 가 SECOND 이상일 때 감소
        if(currentOrdinal > 0){
            dto.setState(state.values()[currentOrdinal -1]);
        }
        return dto;
    }
}
