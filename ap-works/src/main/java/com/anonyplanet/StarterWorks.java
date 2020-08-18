package com.anonyplanet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 谭应有
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.anonyplanet.mapper")
public class StarterWorks {
    public static void main(String[] args) {
        SpringApplication.run(StarterWorks.class, args);
    }
}
