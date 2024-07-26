package com.insideme.insidemebackend.dto.MongoDB;

public record InsertADocumentRequest(
        String dataSource,
        String database,
        String collection,
        Object document
) {
}
