package com.bscllc.consul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@Configuration
@EnableAutoConfiguration
@EnableRetry
@RefreshScope
public class ClientApplication {
    private Logger log = LoggerFactory.getLogger(ClientApplication.class);

    @Autowired
    MyProperty myProperty;

    @RequestMapping("/health")
    public String health() {
        log.info("Health pong");
        return "pong";
    }

    @RequestMapping("/")
    public String root() {
        log.info("Root request: " + myProperty);

        return "hi";
    }


    public static void main(String... args) {
        SpringApplication.run(ClientApplication.class, args);
    }
}
