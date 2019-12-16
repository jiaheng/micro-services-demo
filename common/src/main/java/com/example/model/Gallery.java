package com.example.model;

import java.util.List;

public class Gallery {

    private int Id;
    private List<Image> images;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
