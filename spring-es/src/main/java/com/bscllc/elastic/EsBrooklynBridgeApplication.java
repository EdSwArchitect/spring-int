package com.bscllc.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EsBrooklynBridgeApplication {
//    private static final Logger log = LoggerFactory.getLogger(BrooklynBridgeApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EsBrooklynBridgeApplication.class, args);
    }
}