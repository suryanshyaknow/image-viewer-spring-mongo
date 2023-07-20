package com.example.ImageViewer.service;

import com.example.ImageViewer.model.ImageModel;
import com.example.ImageViewer.repository.ImageViewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageViewerService implements IImageViewerService {

    @Autowired
    private ImageViewerRepository imageViewerRepository;

    @Override
    public ImageModel saveImage(ImageModel imageModel) {
        return imageViewerRepository.save(imageModel);
    }

    @Override
    public Optional<ImageModel> findImageModelById(String id) {
        return imageViewerRepository.findById(id);
    }
}
