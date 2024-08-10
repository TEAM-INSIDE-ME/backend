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

    public Diaries accessToDiaries(String user_id){
        User user = userService.findUserByUserId(user_id);

        String diaries_id=user.getDiariesIds().get(0);
        Diaries diaries = diariesRepository.findById(diaries_id).get();
        diaries.setId(diaries_id);
        return diaries;
    }

    public void saveADiary(String user_id, CreateADiaryRequest createADiaryRequest) {
        Diaries diaries =accessToDiaries(user_id);

        Diary diary = createADiaryRequest.toEntity();
        diaries.add(diary);
        diariesRepository.save(diaries);
    }

    public Diary getADiary(String user_id, int index) {
        Diaries diaries =accessToDiaries(user_id);

        return diaries.getDiaries().get(index);
    }
}
