package com.insideme.insidemebackend.controller;

import com.insideme.insidemebackend.dto.MongoDB.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/api/mongodb")
@RestController
public class MongoDBController {
    @Qualifier("mongoDBRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Value("${mongodb.app.id}")
    private String appId;

    @Value("${mongodb.api.endpoint}")
    private String endPoint;

    //Find a document
    @PostMapping("/findOne")
    public ResponseEntity<FindADocumentResponse> findOneDocument(@RequestBody ADocumentRequestWithFilter aDocumentRequestWithFilter) {
        String url = endPoint+appId+"/endpoint/data/v1/action/findOne";
        FindADocumentResponse findADocumentResponse = restTemplate.postForObject(url, aDocumentRequestWithFilter, FindADocumentResponse.class);
        return ResponseEntity.ok(findADocumentResponse);
    }

    //Insert a document
    @PostMapping("/insertOne")
    public ResponseEntity<InsertADocumentResponse> insertOneDocument(@RequestBody InsertADocumentRequest insertADocumentRequest) {
        String url = endPoint+appId+"/endpoint/data/v1/action/insertOne";
        InsertADocumentResponse insertADocumentResponse = restTemplate.postForObject(url, insertADocumentRequest, InsertADocumentResponse.class);
        return ResponseEntity.ok(insertADocumentResponse);
    }

    //Update a document
    @PostMapping("/updateOne")
    public ResponseEntity<UpdateADocumentResponse> updateOneDocument(@RequestBody UpdateADocumentRequest updateADocumentRequest) {
        String url = endPoint+appId+"/endpoint/data/v1/action/updateOne";
        UpdateADocumentResponse updateADocumentResponse = restTemplate.postForObject(url, updateADocumentRequest, UpdateADocumentResponse.class);
        return ResponseEntity.ok(updateADocumentResponse);
    }

    //Delete a document
    @PostMapping("/deleteOne")
    public ResponseEntity<DeleteADocumentResponse> deleteOneDocument(@RequestBody ADocumentRequestWithFilter aDocumentRequestWithFilter) {
        String url = endPoint+appId+"/endpoint/data/v1/action/deleteOne";
        DeleteADocumentResponse deleteADocumentResponse = restTemplate.postForObject(url, aDocumentRequestWithFilter, DeleteADocumentResponse.class);
        return ResponseEntity.ok(deleteADocumentResponse);
    }
}
