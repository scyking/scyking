package com.scyking.ucenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class UCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UCenterApplication.class, args);
    }

}
