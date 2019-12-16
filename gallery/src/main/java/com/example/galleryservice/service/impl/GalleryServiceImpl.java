package com.example.galleryservice.service.impl;

import com.example.model.Gallery;
import com.example.model.Image;
import com.example.galleryservice.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GalleryServiceImpl implements GalleryService {

    private static final String IMAGE_SERVICE_URL = "http://image-service";
    private static final String IMAGES_ENPOINT = IMAGE_SERVICE_URL + "/images/";

    private final RestTemplate restTemplate;

    @Autowired
    public GalleryServiceImpl(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Gallery getGallery(final int id) {
        // create gallery object
        final Gallery gallery = new Gallery();
        gallery.setId(id);

        // get list of available images
        final ResponseEntity<List<Image>> responseEntity = restTemplate.exchange(
                IMAGES_ENPOINT,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Image>>() {
                });
        gallery.setImages(responseEntity.getBody());

        return gallery;
    }

}
