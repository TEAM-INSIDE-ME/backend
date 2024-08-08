package com.insideme.insidemebackend.dto.user;

import com.insideme.insidemebackend.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@ToString
@Getter
@Setter
public class CreateUserRequest {
    String user_id;
    String provider;
    String refreshToken;
    String name;
    String email;
    String gender;
    Date birth;
    String job;
    String remind_time;    //유저가 선호하는 시간대
    String purpose; //일기 작성 목적
    int frequency;   //일기 작성 주기

    public User toEntity(CreateUserRequest request){
        User user = new User(null,request.user_id,request.provider,request.refreshToken, request.name,
                request.email, request.gender, request.birth, request.job,
                request.remind_time, request.purpose, request.frequency, null);
        return user;
    }
}
