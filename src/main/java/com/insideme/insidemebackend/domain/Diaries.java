package com.insideme.insidemebackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Document(collection = "diaries")
public class Diaries {
    @Id
    private String id; //몽고디비에서 자동으로 생성하는 id
    List<Diary> diaries  = new ArrayList<>();

    public void add(Diary diary) {
        this.diaries.add((diary));
    }
}
