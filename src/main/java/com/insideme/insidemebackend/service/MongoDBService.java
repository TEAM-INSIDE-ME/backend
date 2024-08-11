package com.insideme.insidemebackend.service;

import com.insideme.insidemebackend.domain.User;
import com.insideme.insidemebackend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class MongoDBService {

    public User findUserByUserId(UserRepository userRepository, String userId) {
        return userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}

