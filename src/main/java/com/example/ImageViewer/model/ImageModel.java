package com.example.ImageViewer.model;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection = "images")
public class ImageModel {

    private final String BASE_URL = "http://localhost:8081/image_viewer";

    @Id
    private String id;

    private byte[] imageBytes;

    private String imageUrl;

    public String getId() {
        return id;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public String getImageUrl() {
        return BASE_URL + this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }

    public void setImageUrl(String imageId) {
        this.imageUrl = imageId;
    }

    public ImageModel(byte[] imageBytes) {
        this.imageBytes = imageBytes;
        this.imageUrl = null;
    }
}

