package com.insideme.insidemebackend.dto.diary;

import com.insideme.insidemebackend.domain.Diary;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@ToString
@Getter
@Setter
public class CreateDiaryRequest {
    private String title;
    private String content;
    private Date createdAt;
    private List<String> imageUrls;
    private String font;

    public Diary toEntity(CreateDiaryRequest request){
        Diary diary = new Diary(null, null, request.title, request.content, request.createdAt,
                request.imageUrls, request.font,null, null, null);
        return diary;
    }
}
