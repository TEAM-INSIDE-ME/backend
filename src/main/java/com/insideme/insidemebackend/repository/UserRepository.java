package com.insideme.insidemebackend.repository;

import com.insideme.insidemebackend.domain.Diaries;
import com.insideme.insidemebackend.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUserId(String user_id);
}
