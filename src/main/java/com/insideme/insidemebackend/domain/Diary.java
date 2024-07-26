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
@ToString
@AllArgsConstructor
@Document(collection = "diary")
public class Diary {
    @Id
    private String diaryId; //몽고디비에서 자동으로 생성하는 id

    private String userID;
    private String title;
    private String content;
    private Date createdAt;
    private List<String> imageUrls;
    private String font;
    private String answer;  //AI 질문에 대한 답변
    private String analysisResult;
    private String emotion;
}
