package com.insideme.insidemebackend.controller;

import ch.qos.logback.core.model.Model;
import com.insideme.insidemebackend.domain.Diary;
import com.insideme.insidemebackend.dto.CreateDiaryRequest;
import com.insideme.insidemebackend.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/diary")
@RestController
public class DiaryController {

    @Autowired
    DiaryService diaryService;

    @PostMapping("/createDiary")
    public ResponseEntity<Diary> createDiary(CreateDiaryRequest createDiaryRequest) {
        Diary newDiary = createDiaryRequest.toEntity(createDiaryRequest);
        Diary savedDiary = diaryService.saveDiary(newDiary);
        return ResponseEntity.ok(savedDiary);
    }

    /*@GetMapping
    public ResponseEntity<List<Diary>> getAllDiary() {
        List<Diary> diaries = diaryService.getAllDiary();
        return ResponseEntity.ok(diaries);
    }*/

    /*@GetMapping("/{diary_id}")
    public ResponseEntity<Diary> getDiaryById(@PathVariable String diary_id) {
        Diary diary = diaryService.getDiaryID();
        return ResponseEntity.ok(diary);
    }*/
}
