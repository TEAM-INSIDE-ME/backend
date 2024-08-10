package com.insideme.insidemebackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Document(collection = "user")
public class User {
    @Id
    private String id;  // MongoDB에서 자동 생성되는 고유 ID
    private String user_id; //OAuth 에서 provider 가 제공하는 고유 사용자 ID
    private String provider;
    private String refresh_token;

    private String name;
    private String email;
    private String remind_time;    //유저가 선호하는 시간대 (알람 전송)
    private String password;
    private int frequency;   //일기 작성 주기
    private int weeks_of_consecutive_success; //주단위 연속 성공
    private int total_time; //총기록시간

    private String gender;
    private Date birth;
    private String job;
    private String purpose; //일기 작성 목적

    private String thread_id;

    private List<String> diaries_ids;

}
