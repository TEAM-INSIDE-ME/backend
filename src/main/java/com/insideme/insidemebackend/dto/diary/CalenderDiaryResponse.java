package com.insideme.insidemebackend.dto.diary;

import lombok.AllArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
public class CalenderDiaryResponse {
    String emotion;
    Date createdAt;
}
