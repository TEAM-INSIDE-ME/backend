package com.insideme.insidemebackend.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
public class Diary {

    private Date created_at;
    private boolean bookmark;
    private String font;
    private String title;
    private String content;
    private List<String> image_urls;
    private String output;
    private String user_answer;

}
