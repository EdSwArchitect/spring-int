package com.bscllc.springjpa.brooklyn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaBrooklynBridgeApplication {
    private static final Logger log = LoggerFactory.getLogger(JpaBrooklynBridgeApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JpaBrooklynBridgeApplication.class, args);
    }
}