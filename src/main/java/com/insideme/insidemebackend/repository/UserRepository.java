package com.insideme.insidemebackend.repository;

import com.insideme.insidemebackend.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
