package com.anonyplanet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 邓启航
 */
@SpringBootApplication
@EnableEurekaClient
public class StarterImage {
    public static void main(String[] args) {
        SpringApplication.run(StarterImage.class, args);
    }
}
