package com.insideme.insidemebackend.service;

import com.insideme.insidemebackend.domain.User;
import com.insideme.insidemebackend.dto.OpenAI.Text;
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
    private final OpenAIService openAIService;
    private final UserMapper userMapper;

    public void createUser(String user_id, String  provider, String refreshToken) {
        User user = new User(null,user_id,provider,refreshToken,
                null,null,null,null,0,0,
                0,null,null,null,null,
                openAIService.createThread().id(),diariesService.createDiaries());

        userRepository.save(user);
    }

    @Transactional
    public String initUserInfo(String userId, InitUserRequest initUserRequest) {
        User user = mongoDBService.findUserByUserId(userRepository, userId);
        userMapper.initUserFromDto(initUserRequest, user);
        userRepository.save(user);

        return openAIService.getAMessageFromEmotionBot(user.getThreadId(),
                "안녕 내 이름은 " + initUserRequest.name() +"이고 " +initUserRequest.birth()+
                "에 태어났어. " +initUserRequest.gender()+"이고, 직업은 "+initUserRequest.job()+
                ". 나는 일기를 일주일 중" + initUserRequest.frequency()+ "번씩 쓰려고 해. 이 일기를 쓰는 목적은 "
                        + initUserRequest.purpose()+"이야","");
    }

    public String chatWithEmotionBot(String userId, Text text){
        User user = mongoDBService.findUserByUserId(userRepository, userId);
        return openAIService.getAMessageFromEmotionBot(user.getThreadId(),text.value()
                ,"");
    }


    @Transactional
    public User updateUserInfo(String userId, UserInfo userInfo) {
        User user = mongoDBService.findUserByUserId(userRepository, userId);
        userMapper.updateUserFromDto(userInfo, user);
        return userRepository.save(user);
    }

    public UserInfo getUserInfo(String userId) {
        User user = mongoDBService.findUserByUserId(userRepository, userId);
        UserInfo userInfo = userMapper.userToUserInfo(user);
        return userInfo;
    }
    public Integer deleteUser(String userId) {
        userRepository.deleteById(userId);
        return 1;
    }

}
