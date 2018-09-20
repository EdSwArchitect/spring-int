package com.bscllc.springjpa.brooklyn;

import com.bscllc.springjpa.brooklyn.entities.BrooklynBridge;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class TestParsing {
    @Test
    public void parseIt() {
        String json = "{\"id\":3,\"startDate\":\"2018-08-27T00:41:48\",\"location\":\"location field\"," +
                "\"pedestrians\":1968,\"towardsM\":1900,\"towardsB\":68,\"weather\":\"clear weather\",\"temperature\":85,\"precipitation\":0.025,\"lat\":12.098,\"lon\":-5.0,\"events\":\"no event\"}";

        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));

        try {
            Object thang =  mapper.readValue(json, BrooklynBridge.class);

            System.out.println("Thang object of: " + thang.getClass().getName());
            System.out.println(thang);

        } catch (IOException e) {
            Assert.fail("Failed: " + e.getMessage());
        }
    }
}
