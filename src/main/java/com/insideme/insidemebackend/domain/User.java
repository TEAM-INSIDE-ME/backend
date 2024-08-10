package com.insideme.insidemebackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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

    @Field("user_id")
    private String userId; //OAuth 에서 provider 가 제공하는 고유 사용자 ID
    private String provider;
    @Field("refresh_token")
    private String refreshToken;

    private String name;
    private String email;
    @Field("remind_time")
    private String remindTime;    //유저가 선호하는 시간대 (알람 전송)
    private String password;
    private int frequency;   //일기 작성 주기
    @Field("weeks_of_consecutive_success")
    private int weeksOfConsecutiveSuccess; //주단위 연속 성공
    @Field("total_time")
    private int totalTime; //총기록시간

    private String gender;
    private Date birth;
    private String job;
    private String purpose; //일기 작성 목적

    @Field("thread_id")
    private String threadId;

    @Field("diaries_ids")
    private List<String> diariesIds;

}
