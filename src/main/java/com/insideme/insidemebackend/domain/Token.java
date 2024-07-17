package com.insideme.insidemebackend.domain;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@AllArgsConstructor
@Document(collection = "token")
public class Token {
    @Id
    private String id;  //mongodb에서 자동으로 생성하는 id
    private String userID;
    private String accessToken;
    private String refreshToken;
    private Date expiresIn;
}
