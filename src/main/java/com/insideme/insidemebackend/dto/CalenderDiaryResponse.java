package com.insideme.insidemebackend.dto;

import lombok.AllArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
public class CalenderDiaryResponse {
    String emotion;
    Date createdAt;
}
