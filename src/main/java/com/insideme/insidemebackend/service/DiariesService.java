package com.insideme.insidemebackend.service;

import com.insideme.insidemebackend.domain.Diaries;
import com.insideme.insidemebackend.domain.Diary;
import com.insideme.insidemebackend.dto.diaries.CreateADiaryRequest;
import com.insideme.insidemebackend.repository.DiariesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiariesService {

    @Autowired
    DiariesRepository diariesRepository;
    public Diaries findDiariesById(String id){
        return diariesRepository.findById(id).get();
    }
    public void saveADiary(CreateADiaryRequest createADiaryRequest) {
        Diaries diaries = findDiariesById("66b4fd26521a923404bae37a");
        diaries.setId("66b4fd26521a923404bae37a");
        Diary diary = createADiaryRequest.toEntity();
        System.out.println(diaries);
        diaries.add(diary);
        diariesRepository.save(diaries);
    }




}
