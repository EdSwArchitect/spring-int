package com.bscllc.elastic.spring;

import com.bscllc.elastic.ESBrooklyn;
import com.bscllc.elastic.ESTop;
import com.bscllc.elastic.Hit;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchBrooklynTest {
    private Logger log = LoggerFactory.getLogger(SearchBrooklynTest.class);

    @Test
    public void testSearchAll() {
        try {
            SearchBrooklyn sb = new SearchBrooklyn("ec2-35-174-167-234.compute-1.amazonaws.com", "mybrooklyn");

            List<ESBrooklyn> results = sb.searchAll(0, 100);

            log.info("Number of hits: " + results.size());

            for (ESBrooklyn esb : results) {
                log.info(esb.toString());
            }

        } //
        catch (Exception e) {
            log.error("Error: ", e);
        }
    }

    @Test
    public void testSearchAllString() {
        try {
            SearchBrooklyn sb = new SearchBrooklyn("ec2-52-87-224-50.compute-1.amazonaws.com", "mybrooklyn");

            String results = sb.searchAllString(0, 100);

            log.info("Here we go\n"+ results);

            getHits(results);

        } //
        catch (Exception e) {
            log.error("Error: ", e);
        }
    }

    private List<Hit> getHits(String results) {
        ArrayList<Hit> list = new ArrayList<Hit>();

        if (results != null) {
            ObjectMapper mapper = new ObjectMapper();

            JsonFactory jf = null;
            try {
                JsonParser parser  = new JsonFactory().createParser(results);

                MappingIterator<ESTop>iterator = mapper.readValues(parser, ESTop.class);

                while (iterator.hasNextValue()) {
                    ESTop top = iterator.nextValue();

                    Hit[] hits = top.getHits().getHits();

                    for (Hit hit : hits) {
                        list.add(hit);
                    } // for (Hit hit : hits) {
                } // while (iterator.hasNextValue()) {

            } catch (IOException e) {
                e.printStackTrace();
            }
        } // if (results != null) {
        return list;

    }


    // Black Friday

    @Test
    public void queryTest() {

        try {
            SearchBrooklyn sb = new SearchBrooklyn("ec2-35-174-167-234.compute-1.amazonaws.com", "mybrooklyn");
            List<ESBrooklyn> results = sb.searchByEvent("Black Friday", 0, 100);

            log.info("Results size: " + results.size());
            log.info(results.toString());
            for (ESBrooklyn hit : results) {
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

            List<ESBrooklyn> results = sb.searchByDateRange(startDate, endDate, 0, 240);

            log.info("Results size: " + results.size());
            log.info(results.toString());
            for (ESBrooklyn hit : results) {
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