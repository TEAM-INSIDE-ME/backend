package com.insideme.insidemebackend.dto.MongoDB;

import com.insideme.insidemebackend.domain.Diary;
import com.insideme.insidemebackend.domain.MongoDB.Document;

public record TestReq(
        String dataSource,
        String database,
        String collection,
        Object document
) {
}
