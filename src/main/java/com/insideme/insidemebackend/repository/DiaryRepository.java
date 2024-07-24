package com.insideme.insidemebackend.repository;

import com.insideme.insidemebackend.domain.Diary;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiaryRepository extends MongoRepository<Diary, String> {
}
