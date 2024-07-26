package com.insideme.insidemebackend.dto.MongoDB;

public record ADocumentRequestWithFilter(
        String dataSource,
        String database,
        String collection,
        Object filter
) {
}
