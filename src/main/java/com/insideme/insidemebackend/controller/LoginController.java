package com.insideme.insidemebackend.controller;

import com.insideme.insidemebackend.domain.User;
import com.insideme.insidemebackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Slf4j
@RequestMapping("/api/user")
@RestController
public class LoginController {

    private static final String KAKAO_USER_INFO_URI = "https://kapi.kakao.com/v2/user/me";

    @Qualifier("restTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;
    @PostMapping("/kakao")
    public ResponseEntity<Map<String, Object>> login(
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

            //Map<String, Object> kakaoAccount = (Map<String, Object>) userInfo.get("kakao_account");
            //Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
            //String profileNickname = profile.get("nickname").toString();
            User newUser = new User(null,user_id,"Kakao",refreshToken,
                    name,null,null,"1111",1,0,
                    0,null,null,null,null,null, Collections.singletonList("66b4fd26521a923404bae37a"));
            userService.saveUser(newUser);

            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            System.out.println("Exception occurred while retrieving user info from Kakao");
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "서버 오류 발생"));
        }
    }

}
