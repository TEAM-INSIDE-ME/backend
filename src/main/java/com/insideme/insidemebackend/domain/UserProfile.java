package com.insideme.insidemebackend.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "user_profile")
public class UserProfile {
    @Id
    private String id;

    private String userId;
    private int writingDays;
    private int recordTime;

}
