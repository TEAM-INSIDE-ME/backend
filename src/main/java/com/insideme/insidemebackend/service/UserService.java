package com.insideme.insidemebackend.service;

import com.insideme.insidemebackend.domain.User;
import com.insideme.insidemebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

}
