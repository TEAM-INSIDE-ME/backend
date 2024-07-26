package com.insideme.insidemebackend.dto.MongoDB;

import java.util.List;
import java.util.Map;

public record UpdateADocumentRequest(
        String dataSource,
        String database,
        String collection,
        Object filter,
        Object update
) {
}
