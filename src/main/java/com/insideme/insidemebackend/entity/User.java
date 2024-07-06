package com.insideme.insidemebackend.entity;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@AllArgsConstructor
@ToString
@Document(collection = "users")
public class User {
    @Id
    private String id;  // MongoDB에서 자동 생성되는 고유 ID
    private String userID; //OAuth 에서 provider 가 제공하는 고유 사용자 ID
    private String provider;
    private String name;
    private String email;
    private String profileImage;
    private String phoneNumber;
    private String accessToken;
    private String refreshToken;
    private Date createdAt;
    private Date updatedAt;

}
