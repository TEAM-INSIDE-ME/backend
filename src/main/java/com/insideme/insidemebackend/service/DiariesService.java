package com.insideme.insidemebackend.service;

import com.insideme.insidemebackend.domain.Diaries;
import com.insideme.insidemebackend.domain.Diary;
import com.insideme.insidemebackend.domain.User;
import com.insideme.insidemebackend.dto.diaries.CreateADiaryRequest;
import com.insideme.insidemebackend.repository.DiariesRepository;
import com.insideme.insidemebackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiariesService {
    private final UserRepository userRepository;
    private final DiariesRepository diariesRepository;
    private final MongoDBService mongoDBService;
    private final OpenAIService openAIService;
    private final ImageService imageService;

    private static final String ADDITIONAL_INSTRUCTIONS_FOR_DIARY_ENTRY
            = "Please read this diary and select the image number using file_search that best represents the user's mood. Analyze the diary and ask a question that will make the user's life better, and get to know the user better. So response in this {number, analyze_question} format.";


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

    private String getOutput(String userId, String content){
        User user = mongoDBService.findUserByUserId(userRepository, userId);
        return openAIService.getAMessageFromEmotionBot(
                user.getThreadId(),content,ADDITIONAL_INSTRUCTIONS_FOR_DIARY_ENTRY);
    }

    public String createADiary(String userId, CreateADiaryRequest createADiaryRequest, List<MultipartFile> images) throws IOException {
        Diaries diaries = getDiariesByUserId(userId);
        log.info(diaries.toString());
        List<String> imageIds = imageService.saveImages(images);    //image 저장
        Diary diary = createADiaryRequest.toEntity();

        diary.setOutput(getOutput(userId, diary.getContent()));
        diary.setImage_urls(imageIds);

        diaries.add(diary);
        diariesRepository.save(diaries);

        return diary.getOutput();
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
