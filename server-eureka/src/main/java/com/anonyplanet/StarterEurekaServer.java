package com.anonyplanet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Linzeyu
 * @create 2020-08-19 01:06
 * @describe
 * @state todo
 */
@SpringBootApplication
@EnableEurekaServer
public class StarterEurekaServer {
    public static void main(String[] args) {
        SpringApplication.run(StarterEurekaServer.class, args);
    }
}
