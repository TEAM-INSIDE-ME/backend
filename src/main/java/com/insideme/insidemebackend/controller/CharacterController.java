package com.insideme.insidemebackend.controller;

import com.insideme.insidemebackend.domain.Character;
import com.insideme.insidemebackend.dto.characterDto.CreateRequest;
import com.insideme.insidemebackend.service.CharacterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/character")
@RestController
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @PostMapping("/createCharacter")
    public ResponseEntity<Character> createCharacter() {
        Character newCharacter = characterService.initCharacter();
        Character savedCharacter = characterService.CreateCharacter(newCharacter);
        return ResponseEntity.ok(savedCharacter);
    }
}
