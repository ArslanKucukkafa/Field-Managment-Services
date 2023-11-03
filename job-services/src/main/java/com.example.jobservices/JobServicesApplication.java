package com.example.jobservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class JobServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobServicesApplication.class, args);
    }

}
