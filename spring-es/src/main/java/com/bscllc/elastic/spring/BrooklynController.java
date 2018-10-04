package com.bscllc.elastic.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class BrooklynController {
    private Logger log = LoggerFactory.getLogger(BrooklynController.class);

    @GetMapping(path = "/searchAll")
    public String /*List<HashMap<String, Object>>*/ searchAll(@RequestParam(name = "start") int start,
                                                              @RequestParam(name="size") int size) {

        log.info("searchAll with start: " + start + " and size: " + size);

        SearchBrooklyn sb = new SearchBrooklyn("ec2-35-174-167-234.compute-1.amazonaws.com", "mybrooklyn");

        List<HashMap<String, Object>>listResults =  sb.searchAll(start, size);


        String results = listResults.toString();

        return results;
    }
}
