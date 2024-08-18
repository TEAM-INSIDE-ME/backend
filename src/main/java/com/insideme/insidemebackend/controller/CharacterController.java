package com.insideme.insidemebackend.controller;

import com.insideme.insidemebackend.domain.Character;
import com.insideme.insidemebackend.service.CharacterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RequestMapping("/api/character")
@RestController
public class CharacterController {

    @Autowired
    private CharacterService characterService;


    //캐릭터 생성하기
    @PostMapping("/createCharacter")
    public ResponseEntity<Character> createCharacter() {
        Character newCharacter = characterService.initCharacter();
        Character savedCharacter = characterService.saveCharacter(newCharacter);
        return ResponseEntity.ok(savedCharacter);
    }

    //캐릭터 불러오기
    @GetMapping("/{userId}")
    public Character findById(@PathVariable String userId) {
        Character character = characterService.getCharacterById(userId);
        System.out.println(userId);
        System.out.println(character.toString());
        return character;
    }

    //캐릭터 업데이트하기
    @PostMapping("/{userId}")
    public ResponseEntity<Optional<Character>> updateCharacter(@PathVariable String userId, boolean achievement){
        Optional<Character> updatedCharacter = characterService.updateCharacter(userId, achievement);
        log.info(updatedCharacter.toString());
        return ResponseEntity.ok(updatedCharacter);
    }
}
