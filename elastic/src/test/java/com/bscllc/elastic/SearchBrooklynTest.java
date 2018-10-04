package com.bscllc.elastic;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class SearchBrooklynTest {
    private SearchBrooklyn brooklyn;
    private Logger log = LoggerFactory.getLogger(SearchBrooklynTest.class);


    @Test
    public void connect() {
    }

    @Test
    public void search() throws Exception {
        brooklyn = new SearchBrooklyn("ec2-35-174-167-234.compute-1.amazonaws.com", "mybrooklyn");

        brooklyn.connect();

        String results = brooklyn.search(0, 50);

        Assert.assertNotEquals("Should have returned results", "", results);

        log.info("Results: " + results);

        brooklyn.close();
    }

    @Test
    public void close() {
    }
}