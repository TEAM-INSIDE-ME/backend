package com.insideme.insidemebackend.dto.MongoDB;

public record UpdateADocumentResponse(
        Integer matchedCount,
        Integer modifiedCount
) {
}
