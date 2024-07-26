package com.insideme.insidemebackend.controller;

import com.insideme.insidemebackend.domain.Diary;
import com.insideme.insidemebackend.dto.diary.CreateDiaryRequest;
import com.insideme.insidemebackend.service.DiaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/diary")
@RestController
public class DiaryController {

    @Autowired
    DiaryService diaryService;

    @PostMapping("/createDiary")
    public ResponseEntity<Diary> createDiary(@RequestBody CreateDiaryRequest createDiaryRequest) {
        log.info("Create diary request:" +  createDiaryRequest.toString());
        Diary newDiary = createDiaryRequest.toEntity(createDiaryRequest);
        Diary savedDiary = diaryService.saveDiary(newDiary);
        log.info("Saved diary:" +  savedDiary.toString());
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
