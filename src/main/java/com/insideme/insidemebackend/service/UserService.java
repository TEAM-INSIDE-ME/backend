package com.insideme.insidemebackend.service;

import com.insideme.insidemebackend.domain.User;
import com.insideme.insidemebackend.dto.user.InitUserRequest;
import com.insideme.insidemebackend.dto.user.UserInfo;
import com.insideme.insidemebackend.repository.UserMapper;
import com.insideme.insidemebackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final DiariesService diariesService;
    private final MongoDBService mongoDBService;
    private final UserMapper userMapper;

    public void createUser(String user_id, String  provider, String refreshToken) {
        User user = new User(null,user_id,provider,refreshToken,
                null,null,null,null,0,0,
                0,null,null,null,null,null,diariesService.createDiaries());
        userRepository.save(user);
    }

    @Transactional
    public void initUserInfo(String userId, InitUserRequest initUserRequest) {
        User user = mongoDBService.findUserByUserId(userRepository, userId);
        userMapper.initUserFromDto(initUserRequest, user);
        userRepository.save(user);
    }

    @Transactional
    public User updateUserInfo(String userId, UserInfo userInfo) {
        User user = mongoDBService.findUserByUserId(userRepository, userId);
        userMapper.updateUserFromDto(userInfo, user);
        return userRepository.save(user);
    }

}
