package com.insideme.insidemebackend.controller;

import com.insideme.insidemebackend.domain.Diaries;
import com.insideme.insidemebackend.domain.Diary;
import com.insideme.insidemebackend.dto.diaries.CreateADiaryRequest;
import com.insideme.insidemebackend.service.DiariesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/diaries")
@RestController
public class DiariesController {

    @Autowired
    DiariesService diariesService;

    @PostMapping("/createADiary/{user_id}")
    public ResponseEntity<CreateADiaryRequest> createADiary(@PathVariable("user_id") String user_id, @RequestBody CreateADiaryRequest createADiaryRequest) {
        diariesService.saveADiary(user_id, createADiaryRequest);

        return ResponseEntity.ok(createADiaryRequest);
    }

    @GetMapping("getADiary/{user_id}")
    public ResponseEntity<Diary> getADiary(@PathVariable("user_id") String user_id, @RequestParam int index) {
        Diary diary = diariesService.getADiary(user_id, index);
        return ResponseEntity.ok(diary);
    }

    /*@GetMapping
    public ResponseEntity<List<Diary>> getAllDiary() {
        List<Diary> diaries = diaryService.getAllDiary();
        return ResponseEntity.ok(diaries);
    }*/


}
