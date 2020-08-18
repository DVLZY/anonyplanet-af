package com.anonyplanet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author Linzeyu
 * @create 2020-08-19 00:50
 * @describe
 * @state todo
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class StarterGateway {
    public static void main(String[] args) {
        SpringApplication.run(StarterGateway.class, args);
    }
}
