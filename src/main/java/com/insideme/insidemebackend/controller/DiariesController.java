package com.insideme.insidemebackend.controller;

import com.insideme.insidemebackend.domain.Diaries;
import com.insideme.insidemebackend.domain.Diary;
import com.insideme.insidemebackend.dto.diaries.CreateADiaryRequest;
import com.insideme.insidemebackend.service.DiariesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/diaries")
@RestController
public class DiariesController {

    @Autowired
    DiariesService diariesService;

    @PostMapping("/createADiary/{user_id}")
    public ResponseEntity<CreateADiaryRequest> createADiary(@PathVariable("user_id") String userId, @RequestBody CreateADiaryRequest createADiaryRequest) {
        diariesService.saveADiary(userId, createADiaryRequest);

        return ResponseEntity.ok(createADiaryRequest);
    }

    @GetMapping("getADiary/{user_id}")
    public ResponseEntity<Diary> getADiary(@PathVariable("user_id") String userId, @RequestParam int index) {
        Diary diary = diariesService.getADiary(userId, index);
        return ResponseEntity.ok(diary);
    }

    @GetMapping("getDiaries/{user_id}")
    public ResponseEntity<Diaries> getDiaries(@PathVariable("user_id") String userId) {
        Diaries diaries = diariesService.getDiaries(userId);
        return ResponseEntity.ok(diaries);
    }

    @PostMapping("/updateADiary/{user_id}")
    public ResponseEntity<Diary> updateADiary(@PathVariable("user_id") String userId, @RequestParam int index, @RequestBody Diary diary) {
        diariesService.updateADiary(userId, index, diary);

        return ResponseEntity.ok(diary);
    }

    @PostMapping("/deleteADiary/{user_id}")
    public ResponseEntity<Integer> deleteADiary(@PathVariable("user_id") String userId, @RequestParam int index) {
        diariesService.deleteADiary(userId, index);

        return ResponseEntity.ok(index);
    }
}
