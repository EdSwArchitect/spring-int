package com.bscllc.springjpa.brooklyn;

import com.bscllc.springjpa.brooklyn.entities.BrooklynBridge;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Map;

import static org.assertj.core.api.BDDAssertions.then;

/**
 * Basic integration tests for service demo application.
 *
 * @author Dave Syer
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"management.port=0"})
public class BrooklynBridgeApplicationTest {

    @LocalServerPort
    private int port;

//    @Value("${local.management.port}")
//    private int mgt;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldReturn200WhenSendingRequestToController() throws Exception {
        String json = "{\"startDate\":\"2018-08-27T00:41:48\",\"location\":\"my time goober location field\"," +
                "\"pedestrians\":1968,\"towardsM\":1900,\"towardsB\":68,\"weather\":\"clear weather\",\"temperature\":85,\"precipitation\":0.025,\"lat\":12.098,\"lon\":-5.0,\"events\":\"no event\"}";

        @SuppressWarnings("rawtypes")
        ResponseEntity<BrooklynBridge> entity = this.testRestTemplate.getForEntity(
                "http://localhost:" + this.port + "/brooklyn/add", BrooklynBridge.class);

        ObjectMapper mapper = new ObjectMapper();

        // 2018-08-27T00:41:48

        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));

        BrooklynBridge bb  =  mapper.readValue(json, BrooklynBridge.class);

        testRestTemplate.put("http://localhost:" + this.port + "/brooklyn/add", bb);

    }


}