package com.insideme.insidemebackend.service;

import com.insideme.insidemebackend.domain.Diaries;
import com.insideme.insidemebackend.domain.Diary;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService {

    private final GridFsTemplate gridFsTemplate;

    public GridFsResource getImage(String imageId) throws FileNotFoundException {
        GridFSFile file = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(imageId)));
        return gridFsTemplate.getResource(file);
    }

    public List<GridFsResource> getImages(List<String> imageIds){
        return imageIds.stream().map(id -> {
            try{
                return getImage(id);
            }catch(FileNotFoundException e){
                throw new RuntimeException("File with ID " + id + " not found.");
            }
        }).collect(Collectors.toList());
    }

    @Transactional
    public String saveImage(MultipartFile file) throws IOException {
        ObjectId fileId = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType());
        return fileId.toString();
    }

    @Transactional
    public List<String> saveImages(List<MultipartFile> files){
        return files.stream().map(file -> {
            try{
                return saveImage(file);
            }catch (IOException e){
                throw new RuntimeException("Failed to save image " + file.getOriginalFilename());
            }
        }).collect(Collectors.toList());
    }

    public void deleteImage(String imageId){
        gridFsTemplate.delete(Query.query(Criteria.where("_id").is(new ObjectId(imageId))));
    }

}
