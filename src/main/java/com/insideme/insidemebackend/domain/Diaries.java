package com.insideme.insidemebackend.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@ToString
@Document(collection = "diaries")
public class Diaries {
    @Id
    private String id; //몽고디비에서 자동으로 생성하는 id
    private List<Diary> diaries;

    public void add(Diary diary) {
        this.diaries.add((diary));
    }
    public void set(int index,Diary diary) {
        this.diaries.set(index,diary);
    }

    public void remove(int index){
        this.diaries.remove(index);
    }


    public Diaries(String id, List<Diary> diaries) {
        this.id = id;
        this.diaries = (diaries != null) ? diaries : new ArrayList<>();
    }
}
