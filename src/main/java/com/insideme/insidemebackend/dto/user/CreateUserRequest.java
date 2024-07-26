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
    String name;
    String email;
    String sex;
    Date birth;
    String job;
    String time;    //유저가 선호하는 시간대
    String purpose; //일기 작성 목적
    int reminder;   //일기 작성 주기

    public User toEntity(CreateUserRequest request){
        User user = new User(null, request.name,
                request.email, request.sex, request.birth, request.job,
                request.time, request.purpose, request.reminder, null);
        return user;
    }
}
