package com.insideme.insidemebackend.repository;

import com.insideme.insidemebackend.domain.Character;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CharacterRepository extends MongoRepository<Character, String> {
}
