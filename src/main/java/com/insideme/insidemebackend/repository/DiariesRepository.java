package com.insideme.insidemebackend.repository;

import com.insideme.insidemebackend.domain.Diaries;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface DiariesRepository extends MongoRepository<Diaries, String> {

}
