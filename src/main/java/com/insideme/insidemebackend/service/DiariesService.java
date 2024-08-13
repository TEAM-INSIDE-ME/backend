package com.insideme.insidemebackend.service;

import com.insideme.insidemebackend.domain.Diaries;
import com.insideme.insidemebackend.domain.Diary;
import com.insideme.insidemebackend.domain.User;
import com.insideme.insidemebackend.dto.diaries.CreateADiaryRequest;
import com.insideme.insidemebackend.repository.DiariesRepository;
import com.insideme.insidemebackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiariesService {
    private final UserRepository userRepository;
    private final DiariesRepository diariesRepository;
    private final MongoDBService mongoDBService;
    private final OpenAIService openAIService;

    private static final String ADDITIONAL_INSTRUCTIONS_FOR_DIARY_ENTRY
            = "Please read this diary and select the image number from the file that best represents the user's mood. Analyze the diary and ask a question that will make the user's day look back, make the user's life better, and get to know the user better. So please send {number, analyze_question} in json format.";


    public List<String> createDiaries(){
        List<Diary> emptyList = new ArrayList<>();
        Diaries diaries = new Diaries(null, emptyList);
        return Collections.singletonList(diariesRepository.save(diaries).getId());
    }

    private Diaries getDiariesByUserId(String userId) {
        User user = mongoDBService.findUserByUserId(userRepository, userId);
        String diariesId = user.getDiariesIds().get(0);
        return diariesRepository.findById(diariesId).orElseThrow(() ->
                new IllegalArgumentException("No diaries found for id: " + diariesId));
    }

    public void saveADiary(String userId, CreateADiaryRequest createADiaryRequest) {
        Diaries diaries = getDiariesByUserId(userId);
        User user = mongoDBService.findUserByUserId(userRepository, userId);
        Diary diary = createADiaryRequest.toEntity();

        diary.setAnalysis_question(openAIService.getAMessageFromEmotionBot(
                user.getThreadId(),createADiaryRequest.content(),ADDITIONAL_INSTRUCTIONS_FOR_DIARY_ENTRY));

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
