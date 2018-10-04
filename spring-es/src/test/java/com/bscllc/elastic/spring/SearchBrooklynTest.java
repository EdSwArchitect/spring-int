package com.bscllc.elastic.spring;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class SearchBrooklynTest {
    private Logger log = LoggerFactory.getLogger(SearchBrooklynTest.class);

    @Test
    public void testSearchAll() {
        try {
            SearchBrooklyn sb = new SearchBrooklyn("ec2-35-174-167-234.compute-1.amazonaws.com", "mybrooklyn");

            List<HashMap<String, Object>> results = sb.searchAll(0, 100);

            log.info("Number of hits: " + results.size());

            for (HashMap<String, Object> hit : results) {
                log.info("\t" + hit);
                log.info("====");
            }

        } //
        catch (Exception e) {
            log.error("Error: ", e);

        }

    }

    // Black Friday

    @Test
    public void queryTest() {

        try {
            SearchBrooklyn sb = new SearchBrooklyn("ec2-35-174-167-234.compute-1.amazonaws.com", "mybrooklyn");
            List<HashMap<String, Object>> results = sb.searchByEvent("Black Friday", 0, 100);

            log.info("Results size: " + results.size());
            log.info(results.toString());
            for (HashMap<String, Object> hit : results) {
                log.info("\t" + hit);
                log.info("====");
            }

        } //
        catch (Exception e) {
            log.error("Error: ", e);
        }
    }

    @Test
    public void queryDateTest() {

        try {
            SearchBrooklyn sb = new SearchBrooklyn("ec2-35-174-167-234.compute-1.amazonaws.com", "mybrooklyn");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            // 2017-11-22T15:00:00.000Z

            Date startDate = sdf.parse("2017/11/22 01:00:00");
            Date endDate = sdf.parse("2017/12/26 13:00:00");

            List<HashMap<String, Object>> results = sb.searchByDateRnage(startDate, endDate, 0, 240);

            log.info("Results size: " + results.size());
            log.info(results.toString());
            for (HashMap<String, Object> hit : results) {
                log.info("\t" + hit);
                log.info("====");
            }

        } //
        catch (Exception e) {
            log.error("Error: ", e);
        }
    }

    @Test
    public void formatTest() {
        String sd = "2018/10/01 08:30:00";
        String ed = "2018/10/02 13:30:00";

        StringBuffer query = new StringBuffer();
        query.append("{\n");
        query.append("   \"query\": {\n");
        query.append("        \"range\" : {\n    \"@timestamp\" : {\n");
        query.append("                \"gte\" : \"").append(sd).append("\",\n");
        query.append("                \"lte\" : \"").append(ed).append("\",\n");
        query.append("                \"format\" : \"yyyy/MM/dd HH:mm:ss\"\n");
        query.append("               }\n");
        query.append("          }\n");
        query.append("     }\n");
        query.append(" }\n");


        System.out.println(query.toString());

    }

}