package com.insideme.insidemebackend.service;

import com.insideme.insidemebackend.domain.Diaries;
import com.insideme.insidemebackend.domain.Diary;
import com.insideme.insidemebackend.domain.User;
import com.insideme.insidemebackend.dto.diaries.CreateADiaryRequest;
import com.insideme.insidemebackend.repository.DiariesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiariesService {

    private final DiariesRepository diariesRepository;
    private final UserService userService;

    public Diaries initDiaries(){
        List<Diary> emptyList = new LinkedList<>();
        Diaries diaries = new Diaries(null, emptyList);
        return diaries;
    }

    private Diaries getDiariesByUserId(String userId) {
        User user = userService.findUserByUserId(userId);
        String diariesId = user.getDiariesIds().get(0);
        return diariesRepository.findById(diariesId).orElseThrow(() ->
                new IllegalArgumentException("No diaries found for id: " + diariesId));
    }

    public void saveADiary(String userId, CreateADiaryRequest createADiaryRequest) {
        Diaries diaries = getDiariesByUserId(userId);

        Diary diary = createADiaryRequest.toEntity();
        diaries.add(diary);
        diariesRepository.save(diaries);
    }

    public Diary getADiary(String userId, int index) {
        Diaries diaries = getDiariesByUserId(userId);

        return diaries.getDiaries().get(index);
    }

    public Diaries getDiaries(String userId) {
        return getDiariesByUserId(userId);
    }

    public void updateADiary(String userId, int index, Diary diary) {
        Diaries diaries = getDiariesByUserId(userId);

        diaries.set(index, diary);
        diariesRepository.save(diaries);
    }

    public void deleteADiary(String userId, int index) {
        Diaries diaries = getDiariesByUserId(userId);
        diaries.remove(index);
        diariesRepository.save(diaries);
    }
}
