package com.example.imageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ImageServiceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageServiceDemoApplication.class, args);
	}

}
