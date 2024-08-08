package com.insideme.insidemebackend.controller;

import com.insideme.insidemebackend.domain.Character;
import com.insideme.insidemebackend.domain.User;
import com.insideme.insidemebackend.dto.user.CreateUserRequest;
import com.insideme.insidemebackend.service.CharacterService;
import com.insideme.insidemebackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CharacterService characterService;


    //user 정보 입력 받는 부분이긴 허나 로그인 구현될 시 많이 바뀔 수 있음
    @PostMapping("/createUser")
    public ResponseEntity<User> userCreate(@RequestBody CreateUserRequest createUserRequest) {
        User newUser = createUserRequest.toEntity(createUserRequest);
        User savedUser = userService.saveUser(newUser);
        System.out.println("Saved user: " + savedUser.toString());

        Character newCharacter = characterService.initCharacter();
        newCharacter.setUserId(savedUser.getId());
        Character savedCharacter = characterService.CreateCharacter(newCharacter);
        System.out.println("New character created:" +  savedCharacter.toString());

        return ResponseEntity.ok(savedUser);
    }

}
