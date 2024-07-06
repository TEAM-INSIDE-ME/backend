package com.insideme.insidemebackend.dto;

import com.insideme.insidemebackend.entity.User;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class UserForm {
    private String userId;
    private String provider;
    private String name;
    private String email;
    private String profileImage;
    private String phoneNumber;
    private String accessToken;
    private String refreshToken;

    public User toEntity(){
        return new User(null, userId, provider, name, email, profileImage,
                phoneNumber, accessToken, refreshToken, null, null);
    }
}
