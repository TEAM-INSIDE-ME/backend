package com.insideme.insidemebackend.dto;

import com.insideme.insidemebackend.domain.Diary;

import java.util.Date;
import java.util.List;

public class CreateDiaryRequest {
    private String title;
    private String content;
    private Date createdAt;
    private List<String> imageUrls;
    private String font;

    public Diary toEntity(CreateDiaryRequest request){
        Diary diary = new Diary(null, null, title, content, createdAt,
                imageUrls, font,null, null, null);
        return diary;
    }
}
