package com.insideme.insidemebackend.controller;

import com.insideme.insidemebackend.domain.Diaries;
import com.insideme.insidemebackend.domain.Diary;
import com.insideme.insidemebackend.dto.diaries.CreateADiaryRequest;
import com.insideme.insidemebackend.service.DiariesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/diaries")
@RestController
@RequiredArgsConstructor // auto DI ,final 객체만 DI
public class DiariesController {

    private final DiariesService diariesService;

    @PostMapping("/createADiary/{user_id}")
    public ResponseEntity<String> createADiary(@PathVariable("user_id") String userId, @RequestBody CreateADiaryRequest createADiaryRequest) {
        return ResponseEntity.ok(diariesService.createADiary(userId, createADiaryRequest));
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

    @PutMapping("/updateADiary/{user_id}")
    public ResponseEntity<Diary> updateADiary(@PathVariable("user_id") String userId, @RequestParam int index, @RequestBody Diary diary) {
        diariesService.updateADiary(userId, index, diary);

        return ResponseEntity.ok(diary);
    }

    @DeleteMapping("/deleteADiary/{user_id}")
    public ResponseEntity<Integer> deleteADiary(@PathVariable("user_id") String userId, @RequestParam int index) {
        diariesService.deleteADiary(userId, index);

        return ResponseEntity.ok(index);
    }
}
