package com.insideme.insidemebackend.dto.user;


import java.util.Date;

public record UserInfo(

        String name,
        String email,
        String remindTime,    //유저가 선호하는 시간대 (알람 전송)
        String password,
        Integer frequency,   //일기 작성 주기
        Integer weeksOfConsecutiveSuccess, //주단위 연속 성공
        Integer totalMinute, //총기록시간

        String gender,
        Date birth,
        String job,
        String purpose //일기 작성 목적
) {
}
