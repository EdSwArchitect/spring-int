package com.bscllc.elastic.spring;

import com.bscllc.elastic.ESBrooklyn;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/es-brooklyn")
public class BrooklynController {
    private Logger log = LoggerFactory.getLogger(BrooklynController.class);

    @Autowired
    @Value("${elastic.host.name}")
    private String hostName;
    @Autowired
    @Value("${elastic.index}")
    private String index;

    @GetMapping(path = "/searchAll")
    public ResponseEntity<List<ESBrooklyn>> searchAll(@RequestParam(name = "start", defaultValue="0") int start,
                                                              @RequestParam(name="size", defaultValue="10") int size) {

        log.info("searchAll with start: " + start + " and size: " + size);

        SearchBrooklyn sb = new SearchBrooklyn(hostName, index);

        List<ESBrooklyn>listResults =  sb.searchAll(start, size);

        return new ResponseEntity<List<ESBrooklyn>>(listResults, HttpStatus.OK);
    }

    @GetMapping(path = "/searchByEvent")
    public ResponseEntity<List<ESBrooklyn>> /*List<HashMap<String, Object>>*/ searchByEvent(
            @RequestParam(name = "event") String event,
            @RequestParam(name = "start", defaultValue = "0") int start,
            @RequestParam(name="size", defaultValue = "10") int size) {

        log.info("searchByEvent '" + event + "' with start: " + start + " and size: " + size);

        SearchBrooklyn sb = new SearchBrooklyn(hostName, index);

        List<ESBrooklyn>listResults = sb.searchByEvent(event, start, size);

        return new ResponseEntity<List<ESBrooklyn>>(listResults, HttpStatus.OK);
    }

    @GetMapping(path = "/searchByDateRange")
    public ResponseEntity<List<ESBrooklyn>> /*List<HashMap<String, Object>>*/ searchByDateRange(
            @RequestParam(name = "startDate") String startDate,
            @RequestParam(name = "endDate") String endDate,
            @RequestParam(name = "start", defaultValue = "0") int start,
            @RequestParam(name="size", defaultValue = "10") int size) {

        log.info("searchByDateRange '" + startDate + "' - '" + endDate + "' with start: " + start + " and size: " + size);

        SearchBrooklyn sb = new SearchBrooklyn(hostName, index);

        Date sd = null;
        Date ed = null;

        List<ESBrooklyn>listResults = sb.searchByDateRange(sd,ed,start, size);

        return new ResponseEntity<List<ESBrooklyn>>(listResults, HttpStatus.OK);
    }

    private ArrayList<ESBrooklyn>parseIt(String brooklyn) {
        ArrayList<ESBrooklyn> esb = new ArrayList<ESBrooklyn>();

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonParser jsonParser = new JsonFactory().createParser(brooklyn);

            MappingIterator<ESBrooklyn>iterator = mapper.readValues(jsonParser, ESBrooklyn.class);

            while (iterator.hasNextValue()) {
                esb.add(iterator.next());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return esb;
    }

}
