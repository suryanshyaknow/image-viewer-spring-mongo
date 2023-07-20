package com.example.ImageViewer.repository;

import com.example.ImageViewer.model.ImageModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageViewerRepository extends MongoRepository<ImageModel, String> {

}
