package com.insideme.insidemebackend.repository;

import com.insideme.insidemebackend.domain.Character;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CharacterRepository extends MongoRepository<Character, String> {
    //Optional<Character> findByUserId(String userId);
    Character findByUserId(String userId);
}
