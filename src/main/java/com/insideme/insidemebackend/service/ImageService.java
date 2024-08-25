package com.insideme.insidemebackend.service;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Transactional
    public String saveImage(MultipartFile file) throws IOException {
        ObjectId fileId = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType());
        return fileId.toString();
    }

    public GridFsResource getImage(String imageId) {
        GridFSFile file = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(imageId)));
        return gridFsTemplate.getResource(file);
    }

    @Transactional
    public List<String> saveImages(List<MultipartFile> files) throws IOException {
        return files.stream().map(file -> {
            try{
                return saveImage(file);
            }catch (IOException e){
                throw new RuntimeException("Failed to save image " + file.getOriginalFilename());
            }
        }).collect(Collectors.toList());
    }

    @Transactional
    public void deleteImage(String imageId){
        gridFsTemplate.delete(Query.query(Criteria.where("_id").is("imageId")));
    }

}
