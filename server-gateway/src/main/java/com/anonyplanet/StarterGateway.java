package com.anonyplanet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author 高祎蒙
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class StarterGateway {
    public static void main(String[] args) {
        SpringApplication.run(StarterGateway.class, args);
    }
}
