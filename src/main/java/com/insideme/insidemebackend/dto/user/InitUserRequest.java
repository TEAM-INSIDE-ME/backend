package com.insideme.insidemebackend.dto.user;


import java.util.Date;

public record InitUserRequest(
        String name,
        String remindTime,
        String password,
        Integer frequency,
        String gender,
        Date birth,
        String job,
        String purpose
) {
}
