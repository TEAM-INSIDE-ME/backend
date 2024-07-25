package com.insideme.insidemebackend.controller;

import com.insideme.insidemebackend.dto.MongoDB.TestReq;
import com.insideme.insidemebackend.dto.MongoDB.TestRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/api/mongodb")
@RestController
public class MongoDBController {
    @Qualifier("mongoDBrestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Value("${mongodb.app.id}")
    private String appId;

    //Insert one document
    @PostMapping("/insertOne")
    public ResponseEntity<TestRes> insertOneDocument(@RequestBody TestReq request) {
        String url = "https://data.mongodb-api.com/app/"+appId+"/endpoint/data/v1/action/insertOne";
        TestRes testRes = restTemplate.postForObject(url, request, TestRes.class);
        return ResponseEntity.ok(testRes);
    }
}
