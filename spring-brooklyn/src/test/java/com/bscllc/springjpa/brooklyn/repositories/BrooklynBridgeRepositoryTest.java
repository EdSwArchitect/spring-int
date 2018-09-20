package com.bscllc.springjpa.brooklyn.repositories;

import com.bscllc.springjpa.brooklyn.entities.BrooklynBridge;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

public class BrooklynBridgeRepositoryTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findByStartDate() {

        Object obj = null;
        BufferedReader in = null;
        String[] header = {
        "hour_beginning","location","Pedestrians","Towards Manhattan","Towards Brooklyn","weather_summary","temperature",
                "precipitation","lat","long","events" };

        long count = 0;
        try {
            FileInputStream fis = new FileInputStream("spring-brooklyn/src/main/resources/brooklyn-bridge-automated" +
                    "-counts.csv");

            in = new BufferedReader(new InputStreamReader(fis, "UTF-8"));

            CsvParserSettings settings = new CsvParserSettings();
            settings.setHeaders();
            settings.setNumberOfRowsToSkip(1L);

            CsvParser parser = new CsvParser(settings);
            Record record;

            parser.beginParsing(in);

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("bscllc");
            EntityManager em = emf.createEntityManager();

            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy H:mm");

            while ((record = parser.parseNextRecord()) != null) {
                BrooklynBridge bb = new BrooklynBridge();

//                bb.setStartDate(record.getDate("hour_beginning"));
//                bb.setLocation(record.getString("location"));
//                bb.setPedestrians(record.getLong("Pedestrians"));
//                bb.setTowardsM(record.getLong("Towards Manhattan"));
//                bb.setTowardsB(record.getLong("Towards Brooklyn"));
//                bb.setWeather(record.getString("weather_summary"));
//                bb.setTemperature(record.getInt("temperature"));
//                bb.setPrecipitation(record.getDouble("precipitation"));
//                bb.setLat(record.getDouble("lat"));
//                bb.setLon(record.getDouble("long"));
//                bb.setEvents(record.getString("events"));

                // 10/1/2017 0:00

                try {

                    bb.setStartDate(sdf.parse(record.getString(0)));
                    bb.setLocation(record.getString(1));
                    bb.setPedestrians(record.getLong(2));
                    bb.setTowardsM(record.getLong(3));
                    bb.setTowardsB(record.getLong(4));
                    bb.setWeather(record.getString(5));
                    bb.setTemperature(record.getInt(6));
                    bb.setPrecipitation(record.getDouble(7));
                    bb.setLat(record.getDouble(8));
                    bb.setLon(record.getDouble(9));
                    bb.setEvents(record.getString(10));

                    em.persist(bb);

                    if (++count % 100 == 0) {
                        transaction.commit();
                        transaction.begin();
                    }
                }
                catch(NullPointerException npe) {
                    System.err.println("Bad record: '" + ++count);
                }
            } // while ((record = parser.parseNextRecord()) != null) {

                transaction.commit();

            in.close();

        } catch (IOException | ParseException e) {
            System.err.println("Line count: " + count);
            e.printStackTrace();
        }
        catch(IllegalArgumentException e) {
            System.err.println("Line count: " + count);
            e.printStackTrace();
        }
        catch(Exception e) {
            System.err.println("Line count: " + count);
            e.printStackTrace();
        }
    }
}