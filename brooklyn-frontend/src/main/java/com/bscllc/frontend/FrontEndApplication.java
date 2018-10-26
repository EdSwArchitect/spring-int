package com.bscllc.frontend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class FrontEndApplication {
    private Logger log = LoggerFactory.getLogger(FrontEndApplication.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @LoadBalanced
    @Bean
    public RestTemplate loadbalancedRestTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/health")
    public String health() {
        log.info("Health pong");
        return "pong";
    }

    @RequestMapping("/")
    public String root() {
        return "hi";
    }

    @RequestMapping("/discovery")
    public String discovery() {
        StringBuilder sb = new StringBuilder("<HTML><BODY><PRE>\n");

        sb.append("Description: ").append(discoveryClient.description()).append("\n");
        List<String>services = discoveryClient.getServices();

        services.forEach(service -> {
            List<ServiceInstance>serviceInstances = discoveryClient.getInstances(service);

            serviceInstances.forEach(serviceInstance -> {
                sb.append(service).append(":").append(serviceInstance.getUri().toString());
                sb.append(" id :").append(serviceInstance.getServiceId()).append("\n");
                sb.append("       metadata: ").append(serviceInstance.getMetadata()).append("\n");
                sb.append("       class: ").append(serviceInstance.getClass().getName()).append("\n");
            });

        });

        sb.append("</PRE></BODY></HTML>\n");
        return sb.toString();
    }


    @RequestMapping("/strServices")
    public String client() {
        List<String>services = discoveryClient.getServices();
        String json = "";
        List<Service> mySvcs = new ArrayList<Service>();

        for (String service : services) {
            List<ServiceInstance>instances = discoveryClient.getInstances(service);

            for (ServiceInstance instance : instances) {
                String id = instance.getServiceId();
                String uri = instance.getUri().toString();
                String desc = discoveryClient.description();

                mySvcs.add(new Service(uri,id, desc));
            }
        }

        DistributedSvcs svcs = new DistributedSvcs();
        svcs.setDescription(discoveryClient.description());
        svcs.setServices(mySvcs);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            json = objectMapper.writeValueAsString(svcs);
        } //
        catch (JsonProcessingException jpe) {
            log.error("Error going from string array to json", jpe);

        }

        return json;
    }

    @RequestMapping("/services")
    public ResponseEntity<DistributedSvcs> services() {
        List<String>services = discoveryClient.getServices();
        List<Service> mySvcs = new ArrayList<Service>();

        for (String service : services) {
            List<ServiceInstance>instances = discoveryClient.getInstances(service);

            for (ServiceInstance instance : instances) {
                String id = instance.getServiceId();
                String uri = instance.getUri().toString();
                String desc = discoveryClient.description();

                mySvcs.add(new Service(uri,id, desc));
            }
        }

        DistributedSvcs svcs = new DistributedSvcs();
        svcs.setDescription(discoveryClient.description());
        svcs.setServices(mySvcs);

        return new ResponseEntity<DistributedSvcs>(svcs, HttpStatus.OK);
    }

    public static void main(String... args) {
        SpringApplication.run(FrontEndApplication.class, args);
    }
}
