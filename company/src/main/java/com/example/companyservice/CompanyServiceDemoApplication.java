package com.example.companyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CompanyServiceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompanyServiceDemoApplication.class, args);
    }

}
