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

    @PostMapping("/createADiary")
    public ResponseEntity<CreateADiaryRequest> createADiary(@RequestBody CreateADiaryRequest createADiaryRequest) {
        log.info("Create diary request:" +  createADiaryRequest.toString());
        diariesService.saveADiary(createADiaryRequest);
        //log.info("Saved diary:" +  savedDiary.toString());
        return ResponseEntity.ok(createADiaryRequest);
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
