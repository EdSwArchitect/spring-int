package gumby;

import com.bscllc.elastic.ESBrooklyn;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class TestJsonArray {
    public static String JSON = "[{towards_brooklyn=426, location=Brooklyn Bridge, lat=40.70816397, " +
            "towards_manhattan=645, " +
            "@timestamp=2017-10-02T14:00:00.000Z, weather_summary=clear-day, temperature=60, events=null, Pedestrians=1071, lon=-73.9995087, precipitation=0.0}, {towards_brooklyn=757, location=Brooklyn Bridge, lat=40.70816397, towards_manhattan=860, @timestamp=2017-10-02T15:00:00.000Z, weather_summary=clear-day, temperature=64, events=null, Pedestrians=1617, lon=-73.9995087, precipitation=0.0}, {towards_brooklyn=855, location=Brooklyn Bridge, lat=40.70816397, towards_manhattan=898, @timestamp=2017-10-02T17:00:00.000Z, weather_summary=clear-day, temperature=68, events=null, Pedestrians=1753, lon=-73.9995087, precipitation=0.0}, {towards_brooklyn=604, location=Brooklyn Bridge, lat=40.70816397, towards_manhattan=1055, @timestamp=2017-10-02T22:00:00.000Z, weather_summary=clear-day, temperature=69, events=null, Pedestrians=1659, lon=-73.9995087, precipitation=0.0}, {towards_brooklyn=32, location=Brooklyn Bridge, lat=40.70816397, towards_manhattan=46, @timestamp=2017-10-03T03:00:00.000Z, weather_summary=clear-night, temperature=57, events=null, Pedestrians=78, lon=-73.9995087, precipitation=0.0}, {towards_brooklyn=228, location=Brooklyn Bridge, lat=40.70816397, towards_manhattan=162, @timestamp=2017-10-03T12:00:00.000Z, weather_summary=clear-day, temperature=52, events=null, Pedestrians=390, lon=-73.9995087, precipitation=0.0}, {towards_brooklyn=381, location=Brooklyn Bridge, lat=40.70816397, towards_manhattan=555, @timestamp=2017-10-03T14:00:00.000Z, weather_summary=clear-day, temperature=62, events=null, Pedestrians=936, lon=-73.9995087, precipitation=2.0E-4}, {towards_brooklyn=699, location=Brooklyn Bridge, lat=40.70816397, towards_manhattan=996, @timestamp=2017-10-03T21:00:00.000Z, weather_summary=clear-day, temperature=66, events=null, Pedestrians=1695, lon=-73.9995087, precipitation=0.0}, {towards_brooklyn=69, location=Brooklyn Bridge, lat=40.70816397, towards_manhattan=65, @timestamp=2017-10-04T02:00:00.000Z, weather_summary=clear-night, temperature=56, events=null, Pedestrians=134, lon=-73.9995087, precipitation=0.0}, {towards_brooklyn=5, location=Brooklyn Bridge, lat=40.70816397, towards_manhattan=11, @timestamp=2017-10-04T05:00:00.000Z, weather_summary=clear-night, temperature=53, events=null, Pedestrians=16, lon=-73.9995087, precipitation=0.0}, {towards_brooklyn=7, location=Brooklyn Bridge, lat=40.70816397, towards_manhattan=5, @timestamp=2017-10-04T07:00:00.000Z, weather_summary=clear-night, temperature=52, events=null, Pedestrians=12, lon=-73.9995087, precipitation=0.0}, {towards_brooklyn=5, location=Brooklyn Bridge, lat=40.70816397, towards_manhattan=6, @timestamp=2017-10-04T08:00:00.000Z, weather_summary=clear-night, temperature=51, events=null, Pedestrians=11, lon=-73.9995087, precipitation=0.0}, {towards_brooklyn=38, location=Brooklyn Bridge, lat=40.70816397, towards_manhattan=68, @timestamp=2017-10-04T10:00:00.000Z, weather_summary=clear-night, temperature=51, events=null, Pedestrians=106, lon=-73.9995087, precipitation=0.0}, {towards_brooklyn=245, location=Brooklyn Bridge, lat=40.70816397, towards_manhattan=150, @timestamp=2017-10-05T00:00:00.000Z, weather_summary=clear-night, temperature=69, events=null, Pedestrians=395, lon=-73.9995087, precipitation=0.0}, {towards_brooklyn=47, location=Brooklyn Bridge, lat=40.70816397, towards_manhattan=54, @timestamp=2017-10-05T10:00:00.000Z, weather_summary=partly-cloudy-night, temperature=63, events=null, Pedestrians=101, lon=-73.9995087, precipitation=0.0}, {towards_brooklyn=174, location=Brooklyn Bridge, lat=40.70816397, towards_manhattan=133, @timestamp=2017-10-05T11:00:00.000Z, weather_summary=partly-cloudy-night, temperature=63, events=null, Pedestrians=307, lon=-73.9995087, precipitation=0.0}, {towards_brooklyn=295, location=Brooklyn Bridge, lat=40.70816397, towards_manhattan=392, @timestamp=2017-10-05T13:00:00.000Z, weather_summary=clear-day, temperature=65, events=null, Pedestrians=687, lon=-73.9995087, precipitation=0.0}, {towards_brooklyn=441, location=Brooklyn Bridge, lat=40.70816397, towards_manhattan=640, @timestamp=2017-10-05T14:00:00.000Z, weather_summary=clear-day, temperature=69, events=null, Pedestrians=1081, lon=-73.9995087, precipitation=9.0E-4}, {towards_brooklyn=904, location=Brooklyn Bridge, lat=40.70816397, towards_manhattan=908, @timestamp=2017-10-05T18:00:00.000Z, weather_summary=partly-cloudy-day, temperature=80, events=null, Pedestrians=1812, lon=-73.9995087, precipitation=0.0021}, {towards_brooklyn=827, location=Brooklyn Bridge, lat=40.70816397, towards_manhattan=1129, @timestamp=2017-10-05T21:00:00.000Z, weather_summary=clear-day, temperature=80, events=null, Pedestrians=1956, lon=-73.9995087, precipitation=0.0}]";

    public static String SINGLE_JSON = "{towards_brooklyn=426, location=Brooklyn Bridge, lat=40.70816397, towards_manhattan=645, @timestamp=2017-10-02T14:00:00.000Z, weather_summary=clear-day, temperature=60, events=null, Pedestrians=1071, lon=-73.9995087, precipitation=0.0}";

    @Test
    public void parseItem() {
        int SIZE = 20;

        ObjectMapper mapper = new ObjectMapper();
        try {
            ESBrooklyn brooklyn = mapper.readValue(SINGLE_JSON, ESBrooklyn.class);

            System.out.println(brooklyn);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }


    @Test
    public void parseArrayTest() {
        int SIZE = 20;

        ArrayList<ESBrooklyn> esb = new ArrayList<ESBrooklyn>();

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonParser jsonParser = new JsonFactory().createParser(JSON);

            MappingIterator<ESBrooklyn> iterator = mapper.readValues(jsonParser, ESBrooklyn.class);

            while (iterator.hasNextValue()) {
                ESBrooklyn e = iterator.next();
                esb.add(e);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertEquals("Should be same size", SIZE, esb.size());

        System.out.println(esb);


    }
}
