package com.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Image {

    private int id;
    private String title;
    private String url;

    @JsonCreator
    public Image(
            @JsonProperty("id") int id,
            @JsonProperty("title") String title,
            @JsonProperty("url") String url) {
        this.id = id;
        this.title = title;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;

    }
}
