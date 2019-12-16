package com.example.galleryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GalleryServiceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GalleryServiceDemoApplication.class, args);
    }

}
