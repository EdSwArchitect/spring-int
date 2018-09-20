package com.bscllc.elastic;

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import static org.elasticsearch.common.xcontent.XContentFactory.*;

import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.List;

public class LoadBrooklyn {
    public static Logger log = LoggerFactory.getLogger(LoadBrooklyn.class);


    public static void main(String... args) {
        String[] headers = {"hour_beginning","location","Pedestrians","Towards Manhattan","Towards Brooklyn",
                "weather_summary","temperature","precipitation","lat","long","events"};
        try {
            BeanListProcessor<Brooklyn> rowProcessor = new BeanListProcessor<Brooklyn>(Brooklyn.class);

            // The settings object provides many configuration options
            CsvParserSettings parserSettings = new CsvParserSettings();

            //You can configure the parser to automatically detect what line separator sequence is in the input
            parserSettings.setNumberOfRowsToSkip(1L);
            parserSettings.setHeaders(headers);
            parserSettings.setProcessor(rowProcessor);

            // creates a parser instance with the given settings
            CsvParser parser = new CsvParser(parserSettings);

            FileInputStream fis = new FileInputStream("C:/DEV/data/small_brooklyn.csv");
            InputStreamReader reader = new InputStreamReader(fis);

            parser.parse(reader);

            List<Brooklyn> beans = rowProcessor.getBeans();

            log.info("The beans size: " + beans.size());

//            Settings settings = Settings.builder()
//                    .put("cluster.name", "myClusterName").build();
//            TransportClient client = new PreBuiltTransportClient(settings);

            TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName(args[0]), 9300));

            BulkRequestBuilder bulkRequest = client.prepareBulk();
            BulkResponse bulkResponse = null;

            int docCount = 0;

            for (Brooklyn line : beans) {
                log.info(line.toString());

                bulkRequest.add(client.prepareIndex(args[1], "doc")
                        .setSource(jsonBuilder()
                                .startObject()
                                .field("Towards Brooklyn", line.getTowardsBrooklyn())
                                .field("location", line.getLocation())
                                .field("lat", line.getLat())
                                .field("Towawrds Manhattan", line.getTowardsManhattan())
                                .field("@timestamp", line.getHourBeginning())
                                .field("weather_summary", line.getWeatherSummary())
                                .field("temperature", line.getTemperature())
                                .field("events", line.getEvents())
                                .field("Pedestrians", line.getPedestrians())
                                .field("lon", line.getLon())
                                .field("precipitation", line.getPrecipitation())
                                .endObject()
                        )
                );

                if (++docCount % 200 == 0) {
                    bulkResponse = bulkRequest.get();

                    if (bulkResponse.hasFailures()) {
                        log.warn("Errors with bulk adding");;
                    }

                    bulkRequest = client.prepareBulk();
                }
            } // for (Brooklyn line : beans) {

            if (bulkRequest.numberOfActions() > 0) {

                log.info("Adding the last " + bulkRequest.numberOfActions() + " documents.");

                bulkResponse = bulkRequest.get();

                if (bulkResponse.hasFailures()) {
                    log.warn("Errors with bulk adding");;
                }

            }

            log.info("Closing connection to elasticsearch");
            client.close();


        } //
        catch(Exception exp) {
            exp.printStackTrace();
        }
    }
}
