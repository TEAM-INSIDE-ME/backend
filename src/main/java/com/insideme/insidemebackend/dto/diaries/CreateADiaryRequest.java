package com.insideme.insidemebackend.dto.diaries;

import com.insideme.insidemebackend.domain.Diary;

import java.util.Date;
import java.util.List;

public record CreateADiaryRequest(

        String font,
        String title,
        String content,
        List<String> image_urls

) {
    public Diary toEntity() {
        Diary diary = new Diary();
        diary.setCreated_at(new Date());
        diary.setFont(this.font);
        diary.setTitle(this.title);
        diary.setContent(this.content);
        diary.setImage_urls(this.image_urls);
        return diary;
    }
}
