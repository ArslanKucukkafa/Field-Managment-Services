package com.example.identitymanagment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class IdentityManagmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdentityManagmentApplication.class, args);
    }

}
