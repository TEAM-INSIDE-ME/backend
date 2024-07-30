package com.insideme.insidemebackend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.util.Map;

@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/api/user/kakao")
    public ResponseEntity<?> login(@RequestHeader("Authorization") String authorizationHeader) {
        String accessToken = authorizationHeader.replace("Bearer ", "");

        logger.debug("Received access token: {}", accessToken);
        System.out.println("Received access token: " + accessToken);

        // 카카오 API를 사용하여 액세스 토큰 검증 및 사용자 정보 가져오기
        String userInfoUri = "https://kapi.kakao.com/v2/user/me";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.exchange(userInfoUri, HttpMethod.GET, entity, Map.class);

        logger.debug("Response from Kakao: {}", response);
        System.out.println("Response from Kakao: " + response);

        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> userInfo = response.getBody();
            // 여기에서 userInfo를 처리하고 필요한 경우 사용자 데이터를 저장합니다.
            return ResponseEntity.ok(userInfo);
        } else {
            return ResponseEntity.status(response.getStatusCode()).body("카카오 사용자 정보 가져오기 실패");
        }
    }
}
