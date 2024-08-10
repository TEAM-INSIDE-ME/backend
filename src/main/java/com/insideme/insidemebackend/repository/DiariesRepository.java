package com.insideme.insidemebackend.repository;

import com.insideme.insidemebackend.domain.Character;
import com.insideme.insidemebackend.domain.Diaries;
import com.insideme.insidemebackend.domain.Diary;
import com.insideme.insidemebackend.dto.diaries.CreateADiaryRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface DiariesRepository extends MongoRepository<Diaries, String> {

}
