package com.bscllc.elastic.spring;

import com.bscllc.elastic.ESBrooklyn;
import com.bscllc.elastic.ESTop;
import com.bscllc.elastic.Hit;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class SearchBrooklyn {
    private Logger log = LoggerFactory.getLogger(SearchBrooklyn.class);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    private String hostname;
    private String index;

    /**
     * Constructor
     *
     * @param hostname
     * @param index
     */
    public SearchBrooklyn(String hostname, String index) {
        this.hostname = hostname;
        this.index = index;
    }

    public void connect() throws UnknownHostException {
        try {

            HttpGet httpGet = new HttpGet(
                    "http://" + hostname + ":9200/" + index);

            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpResponse resp = null;
            HttpEntity entity = null;
            resp = httpclient.execute(httpGet);

            System.out.println("Status line: " + resp.getStatusLine());

            if (resp.getStatusLine().getStatusCode() != 200) {
                httpclient.close();
                throw new UnknownHostException("The hostname: " + hostname + " index " + index + " combination is " +
                        "invalid");
            }

            EntityUtils.consume(entity);
            httpclient.close();

        } //
        catch (IOException ioe) {
            log.error("IO Exception", ioe);
            throw new UnknownHostException("IOException");
        }
    }

    public String countEvents(String events) {
        StringBuffer buf = new StringBuffer();

        try {

            HttpGet httpGet = new HttpGet(
                    "http://" + hostname + ":9200/" + index + "/_doc/_count&q=events:" + URLEncoder.encode(events, "UTF-8"));

            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpResponse resp = null;
            HttpEntity entity = null;
            resp = httpclient.execute(httpGet);

            System.out.println("Status line: " + resp.getStatusLine());

            entity = resp.getEntity();

            InputStream instream = entity.getContent();

            BufferedReader in = new BufferedReader(new InputStreamReader(instream));
            String line;

            while ((line = in.readLine()) != null) {
                buf.append(line).append('\n');
            } // while ((line = in.readLine()) != null) {

            EntityUtils.consume(entity);
            httpclient.close();

        } //
        catch (IOException ioe) {
            log.error("IO Exception", ioe);
        }

        return buf.toString();
    }

    /**
     * @param start
     * @param size
     * @return
     */
    public List<ESBrooklyn> searchAll(int start, int size) {
        StringBuffer buf = new StringBuffer();

        try {

            StringBuilder query = new StringBuilder();
            query.append("{\n").append("\"query\": {\n").append("\"match_all\": {}\n}\n}");

            // "http://ec2-35-174-167-234.compute-1.amazonaws.com:9200/mybrooklyn/_search?from=100&size=150");

            HttpGetWithBody getWithBody = new HttpGetWithBody();

            getWithBody.setEntity((new ByteArrayEntity(query.toString().getBytes("UTF-8"))));
            getWithBody.setURI(new URI("http://" + hostname + ":9200/" + index + "/_search?from=" + start + "&size=" + size));
            getWithBody.addHeader("Accept", "application/json");
            getWithBody.addHeader("Content-Type", "application/json");

            CloseableHttpClient httpclient = HttpClients.createDefault();

            HttpResponse resp = httpclient.execute(getWithBody);
            HttpEntity entity = null;

            resp = httpclient.execute(getWithBody);

            System.out.println("Status line: " + resp.getStatusLine());

            entity = resp.getEntity();

            InputStream instream = entity.getContent();

            BufferedReader in = new BufferedReader(new InputStreamReader(instream));
            String line;

            while ((line = in.readLine()) != null) {
                buf.append(line).append('\n');
            } // while ((line = in.readLine()) != null) {


            EntityUtils.consume(entity);
            httpclient.close();

        } //
        catch (IOException ioe) {
            log.error("IO Exception", ioe);
        } catch (URISyntaxException urise) {
            log.error("URI Exception", urise);
        }

        return getHits(buf.toString());
    }


    /**
     * @param start
     * @param size
     * @return
     */
    public String searchAllString(int start, int size) {
        StringBuffer buf = new StringBuffer();

        try {

            StringBuilder query = new StringBuilder();
            query.append("{\n").append("\"query\": {\n").append("\"match_all\": {}\n}\n}");

            // "http://ec2-35-174-167-234.compute-1.amazonaws.com:9200/mybrooklyn/_search?from=100&size=150");

            HttpGetWithBody getWithBody = new HttpGetWithBody();

            getWithBody.setEntity((new ByteArrayEntity(query.toString().getBytes("UTF-8"))));
            getWithBody.setURI(new URI("http://" + hostname + ":9200/" + index + "/_search?from=" + start + "&size=" + size));
            getWithBody.addHeader("Accept", "application/json");
            getWithBody.addHeader("Content-Type", "application/json");

            CloseableHttpClient httpclient = HttpClients.createDefault();

            HttpResponse resp = httpclient.execute(getWithBody);
            HttpEntity entity = null;

            resp = httpclient.execute(getWithBody);

            System.out.println("Status line: " + resp.getStatusLine());

            entity = resp.getEntity();

            InputStream instream = entity.getContent();

            BufferedReader in = new BufferedReader(new InputStreamReader(instream));
            String line;

            while ((line = in.readLine()) != null) {
                buf.append(line).append('\n');
            } // while ((line = in.readLine()) != null) {


            EntityUtils.consume(entity);
            httpclient.close();

        } //
        catch (IOException ioe) {
            log.error("IO Exception", ioe);
        } catch (URISyntaxException urise) {
            log.error("URI Exception", urise);
        }

        return buf.toString();
    }

    /**
     * @param start
     * @param size
     * @return
     */
    public List<ESBrooklyn> searchByEvent(String event, int start, int size) {
        StringBuffer buf = new StringBuffer();

        try {

            StringBuilder query = new StringBuilder();

            query.append("{\n");
            query.append("   \"query\": {\n");
            query.append("        \"match\" : { \"events\" : \"").append(event).append("\" }\n");
            query.append("    }\n");
            query.append("}\n");


            // "http://ec2-35-174-167-234.compute-1.amazonaws.com:9200/mybrooklyn/_search?from=100&size=150");

            HttpGetWithBody getWithBody = new HttpGetWithBody();

            getWithBody.setEntity((new ByteArrayEntity(query.toString().getBytes("UTF-8"))));
            getWithBody.setURI(new URI("http://" + hostname + ":9200/" + index + "/_search?from=" + start + "&size=" + size));
            getWithBody.addHeader("Accept", "application/json");
            getWithBody.addHeader("Content-Type", "application/json");

            CloseableHttpClient httpclient = HttpClients.createDefault();

            HttpResponse resp = httpclient.execute(getWithBody);
            HttpEntity entity = null;

            resp = httpclient.execute(getWithBody);

            System.out.println("Status line: " + resp.getStatusLine());

            entity = resp.getEntity();

            InputStream instream = entity.getContent();

            BufferedReader in = new BufferedReader(new InputStreamReader(instream));
            String line;

            while ((line = in.readLine()) != null) {
                buf.append(line).append('\n');
            } // while ((line = in.readLine()) != null) {


            EntityUtils.consume(entity);
            httpclient.close();

        } //
        catch (IOException | URISyntaxException ioe) {
            log.error("IO Exception", ioe);
        }

        return getHits(buf.toString());
    }

    // 2017-11-24T12:00:00.000Z
    public List<ESBrooklyn> searchByDateRange(Date startDate, Date endDate, int start, int size) {
        StringBuffer buf = new StringBuffer();

        String sd = sdf.format(startDate);
        String ed = sdf.format(endDate);

        try {

            StringBuilder query = new StringBuilder();

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

            // "http://ec2-35-174-167-234.compute-1.amazonaws.com:9200/mybrooklyn/_search?from=100&size=150");

            HttpGetWithBody getWithBody = new HttpGetWithBody();

            getWithBody.setEntity((new ByteArrayEntity(query.toString().getBytes("UTF-8"))));
            getWithBody.setURI(new URI("http://" + hostname + ":9200/" + index + "/_search?from=" + start + "&size=" + size));
            getWithBody.addHeader("Accept", "application/json");
            getWithBody.addHeader("Content-Type", "application/json");

            CloseableHttpClient httpclient = HttpClients.createDefault();

            HttpResponse resp = httpclient.execute(getWithBody);
            HttpEntity entity = null;

            resp = httpclient.execute(getWithBody);

            System.out.println("Status line: " + resp.getStatusLine());

            entity = resp.getEntity();

            InputStream instream = entity.getContent();

            BufferedReader in = new BufferedReader(new InputStreamReader(instream));
            String line;

            while ((line = in.readLine()) != null) {
                buf.append(line).append('\n');
            } // while ((line = in.readLine()) != null) {


            EntityUtils.consume(entity);
            httpclient.close();

        } //
        catch (IOException | URISyntaxException ioe) {
            log.error("IO Exception", ioe);
        }

        return getHits(buf.toString());
    }


    public boolean isOpen() {
        return true;
    }

    public void close() {
    }

    private List<HashMap<String, Object>>getHitsAsMap(String results) {
        ArrayList<HashMap<String, Object>>list = new ArrayList<>();

        if (results != null) {
            ObjectMapper mapper = new ObjectMapper();
            HashMap<String, Object> map = new HashMap<String, Object>();

            try {
                map = mapper.readValue(results, HashMap.class);
                Object hits = map.get("hits");

                Object dahHits = ((HashMap<String, Object>)hits).get("hits");

                ArrayList<HashMap<String, Object>> hitsList = (ArrayList<HashMap<String, Object>>)dahHits;

                for (HashMap<String, Object> item : hitsList) {

                    HashMap<String, Object> source = (HashMap<String, Object>)item.get("_source");

                    list.add(source);
                } // for (HashMap<String, Object> item : hitsList) {

            } //
            catch (IOException ioe) {
                log.warn("Error processing hits", ioe);
            }

        } // if (results != null) {
        return list;
    }

    private List<ESBrooklyn> getHits(String results) {
        ArrayList<ESBrooklyn> list = new ArrayList<ESBrooklyn>();

        if (results != null) {
            ObjectMapper mapper = new ObjectMapper();

            JsonFactory jf = null;
            try {
                JsonParser parser  = new JsonFactory().createParser(results);

                MappingIterator<ESTop> iterator = mapper.readValues(parser, ESTop.class);

                while (iterator.hasNextValue()) {
                    ESTop top = iterator.nextValue();

                    Hit[] hits = top.getHits().getHits();

                    for (Hit hit : hits) {
                        list.add(hit.get_source());
                    } // for (Hit hit : hits) {
                } // while (iterator.hasNextValue()) {

            } catch (IOException e) {
                e.printStackTrace();
            }
        } // if (results != null) {
        return list;
    }

}
