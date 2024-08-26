package com.insideme.insidemebackend.controller;

import com.insideme.insidemebackend.service.DiariesService;
import com.insideme.insidemebackend.service.ImageService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RequestMapping("/api/images")
@RestController
@Slf4j
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/{imageId}")
    public ResponseEntity<Resource> getImage(@PathVariable("imageId") String imageId){
        log.info("start");
        try{
            GridFsResource gridFsResource  = imageService.getImage(imageId);
            log.info(gridFsResource.getFilename());
            String contentType = gridFsResource.getContentType();
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(gridFsResource);

        }catch (FileNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

}
