package com.example.ImageViewer.service;

import com.example.ImageViewer.model.ImageModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public interface IImageViewerService {

    public ImageModel saveImage(ImageModel imageModel);

    public Optional<ImageModel> findImageModelById(String id);

    public static byte[] convertFileToBytes(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllBytes(path);
    }
}
