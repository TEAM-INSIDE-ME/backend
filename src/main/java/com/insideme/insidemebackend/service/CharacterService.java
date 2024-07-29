package com.insideme.insidemebackend.service;

import com.insideme.insidemebackend.domain.Character;
import com.insideme.insidemebackend.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;


@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    public Character initCharacter(){
        List<String> emptyList = new LinkedList<>();
        Character character = new Character(null, null, Character.CharacterState.FIRST, emptyList);
        return character;
    }
    public Character CreateCharacter(Character character) {
        return characterRepository.save(character);
    }

    public Optional<Character> getCharacterById(String id) {return characterRepository.findByUserId(id);   }
}
