package com.bscllc.springjpa.brooklyn.controllers;

import com.bscllc.springjpa.brooklyn.entities.BrooklynBridge;
import com.bscllc.springjpa.brooklyn.repositories.BrooklynBridgeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/brooklyn") // This means URL's start with /demo (after Application path)
public class BrooklynBridgeController {

    private static final Logger log = LoggerFactory.getLogger(BrooklynBridgeController.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;


    @Autowired // This means to get the bean called brooklynBridgeRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private BrooklynBridgeRepository brooklynBridgeRepository;

    @LoadBalanced
    @Bean
    public RestTemplate loadbalancedRestTemplate() {
        return new RestTemplate();
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<BrooklynBridge> getAll() {
        log.info("Callled getAll()");

        // This returns a JSON or XML with the users
        return brooklynBridgeRepository.findAll();
    }

    @GetMapping(path="/get")
    public @ResponseBody BrooklynBridge getId(@RequestParam(name="id", required=true)long id) {
        log.info("Callled getId(" + id + ")");

        // This returns a JSON or XML with the users
        return brooklynBridgeRepository.findById(id).get();
    }

    @GetMapping(path="/getByEvent")
    public @ResponseBody Iterable<BrooklynBridge> getEvents(@RequestParam(name="event", required=true)String event) {
        log.info("Callled getByEvent(" + event + ")");

        // This returns a JSON or XML with the users
        return brooklynBridgeRepository.findByEvents(event);
    }

    @GetMapping(path="/getPedestrianRange")
    public @ResponseBody Object getPedestrians(@RequestParam(name="start", required=true)long start, @RequestParam(name=
            "max", required=true)long max) {

        Object result =
                brooklynBridgeRepository.findAllByPedestriansGreaterThanEqualAndPedestriansLessThanEqual(start, max);

        log.info("The add path returned object of class: " + result.getClass().getName());
        log.info("It returned: " + result+3
        );

        return result;
    }

    @PutMapping(path="/addAll")
    public @ResponseBody Object putEntity(@RequestBody ArrayList<BrooklynBridge> brooklynBridges) {
        Object results = brooklynBridgeRepository.saveAll(brooklynBridges);

        log.info("The addAll path returned object of class: " + results.getClass().getName());
        return results;
    }

    private BrooklynBridge convertToEntityAttribute(String strang) throws IOException {
        if( strang == null ) {
            return null;
        }


        ObjectMapper mapper = new ObjectMapper();

        // 2018-08-27T00:41:48

        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));

        return mapper.readValue(strang, BrooklynBridge.class);

    }
}
