package com.scyking.kapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class KApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KApiApplication.class, args);
    }

}
