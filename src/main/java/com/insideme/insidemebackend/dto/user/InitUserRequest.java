package com.insideme.insidemebackend.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class InitUserRequest {
    private String name;
    private String remindTime;    //유저가 선호하는 시간대 (알람 전송)
    private String password;
    private Integer frequency;   //일기 작성 주기

    private String gender;
    private Date birth;
    private String job;
    private String purpose; //일기 작성 목적
    private String threadId;
}
