package com.insideme.insidemebackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class LoginController {

    private static final String KAKAO_USER_INFO_URI = "https://kapi.kakao.com/v2/user/me";

    @Qualifier("restTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/api/user/kakao")
    public ResponseEntity<Map<String, Object>> login(@RequestHeader("Authorization") String authorizationHeader) {
        String accessToken = authorizationHeader.replace("Bearer ", "");
        System.out.println("Received access token: " + accessToken);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(KAKAO_USER_INFO_URI, HttpMethod.GET, entity, Map.class);

            System.out.println("Response from Kakao: " + response);

            if (response.getStatusCode().is2xxSuccessful()) {
                Map<String, Object> userInfo = response.getBody();
                System.out.println("User info retrieved from Kakao: " + userInfo);
                // 여기에서 userInfo를 처리하고 필요한 경우 사용자 데이터를 저장합니다.
                return ResponseEntity.ok(userInfo);
            } else {
                System.out.println("Failed to retrieve user info from Kakao: " + response.getStatusCode());
                return ResponseEntity.status(response.getStatusCode()).body(Map.of("error", "카카오 사용자 정보 가져오기 실패"));
            }
        } catch (Exception e) {
            System.out.println("Exception occurred while retrieving user info from Kakao");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "서버 오류 발생"));
        }
    }
}
