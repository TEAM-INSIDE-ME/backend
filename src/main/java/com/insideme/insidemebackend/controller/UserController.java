package com.insideme.insidemebackend.controller;

import com.insideme.insidemebackend.dto.OpenAI.Text;
import com.insideme.insidemebackend.dto.user.InitUserRequest;
import com.insideme.insidemebackend.dto.user.UserInfo;
import com.insideme.insidemebackend.service.OpenAIService;
import com.insideme.insidemebackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RequestMapping("/api/user")
@RestController
@RequiredArgsConstructor // auto DI ,final 객체만 DI
public class UserController {

    private static final String KAKAO_USER_INFO_URI = "https://kapi.kakao.com/v2/user/me";

    @Qualifier("restTemplate")
    @Autowired
    private RestTemplate restTemplate;

    private final UserService userService;
    @PostMapping("/kakao")
    public ResponseEntity<Map<String, Object>> kakaoLogin(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestHeader(value = "X-Refresh-Token", required = false) String refreshToken) {
        String accessToken = authorizationHeader.replace("Bearer ", "");
        System.out.println("Received access token: " + accessToken);
        System.out.println("Received refresh token: " + refreshToken);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        String url = KAKAO_USER_INFO_URI + "?access_token=" + accessToken;

        try {
            Map<String, Object> userInfo = restTemplate.getForObject(url, Map.class, headers);

            System.out.println("User info retrieved from Kakao: " + userInfo);
            String user_id = userInfo.get("id").toString();
            Map<String, Object> properties = (Map<String, Object>) userInfo.get("properties");
            String name = properties.get("nickname").toString();

            userService.createUser(user_id,"Kakao",refreshToken);

            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            System.out.println("Exception occurred while retrieving user info from Kakao");
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "서버 오류 발생"));
        }
    }

    @PostMapping("/initUser/{user_id}")
    public ResponseEntity<String> initUser(@PathVariable("user_id") String userId, @RequestBody InitUserRequest initUserRequest){
        return ResponseEntity.ok(userService.initUserInfo(userId,initUserRequest));
    }

    @PostMapping("/chat/{user_id}")
    public ResponseEntity<String> chatWithEmotionBot(@PathVariable("user_id") String userId, @RequestBody Text text){
        return ResponseEntity.ok(userService.chatWithEmotionBot(userId,text));
    }

    @PutMapping("/updateUserInfo/{user_id}")
    public ResponseEntity<UserInfo> updateUserInfo(@PathVariable("user_id") String userId, @RequestBody UserInfo userInfo){
        userService.updateUserInfo(userId, userInfo);

        return ResponseEntity.ok(userInfo);
    }

    @GetMapping("/getUserInfo/{user_id}")
    public ResponseEntity<UserInfo> getUserInfo(@PathVariable("user_id") String userId){
        return ResponseEntity.ok(userService.getUserInfo(userId));
    }

    @DeleteMapping("/deleteUser/{user_id}")
    public ResponseEntity<Integer> deleteUser(@PathVariable("user_id") String userId){
        return ResponseEntity.ok(userService.deleteUser(userId));
    }
}
