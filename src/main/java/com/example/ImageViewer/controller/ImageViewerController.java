package com.example.ImageViewer.controller;

import com.example.ImageViewer.model.ImageModel;
import com.example.ImageViewer.service.IImageViewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping("image_viewer")
public class ImageViewerController {

    @Autowired
    private final IImageViewerService imageViewerService;

    public ImageViewerController(IImageViewerService imageViewerService) {
        this.imageViewerService = imageViewerService;
    }

    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(@RequestBody ImageModel imageModel) {

        // Saving the image without any URL
        ImageModel savedImage = imageViewerService.saveImage(imageModel);

        // Getting the image's generated id
        String imageId = savedImage.getId();

        // Generating and setting the imageUrl internally after imageId is available
        savedImage.setImageUrl(imageId);

        // Re-saving the image
        imageViewerService.saveImage(savedImage);

        return ResponseEntity.ok(savedImage.getImageUrl());
    }

    @GetMapping("display-image/{id}")
    public ResponseEntity<byte[]> displayImage(@RequestParam String id) {
        Optional<ImageModel> optionalImageModel = imageViewerService.findImageModelById(id);

        // Checking if the ImageModel exists in the database
        if (optionalImageModel.isPresent()) {
            ImageModel imageModel = optionalImageModel.get();
            byte[] imageBytes = imageModel.getImageBytes();

            // Convert the image bytes to a Base64-encoded string
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            // Prepare the response with the data URL
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN); // Set content type as plain text

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if image not found
        }
    }

}
