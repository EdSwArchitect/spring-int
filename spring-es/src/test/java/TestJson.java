import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class TestJson {
    public static void main(String... args) {
        try {
            XContentBuilder builder = jsonBuilder()
                    .startObject()
                    .field("Towards Brooklyn", "towards brooklyn, b")
                    .field("location", 55.5)
                    .field("lat", -2.1104)
                    .field("Towawrds Manhattan", "towards manhattan")
                    .field("@timestamp", new Date())
                    .field("weather_summary", "Sunny")
                    .field("temperature", 40)
                    .field("events", "New Year")
                    .field("Pedestrians", 44)
                    .field("lon", 50.05)
                    .field("precipitation", 20)
                    .endObject();

            System.out.println(builder.string());

            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(
                    "http://ec2-35-174-167-234.compute-1.amazonaws.com:9200/mybrooklyn/_search?from=100&size=150");

            HttpResponse resp = httpclient.execute(httpGet);

            System.out.println("Status line: " + resp.getStatusLine());

            HttpEntity entity = resp.getEntity();

            InputStream instream = entity.getContent();

            BufferedReader in = new BufferedReader(new InputStreamReader(instream));
            String line;

            while ((line = in.readLine()) != null) {
                System.out.println(line);
            } // while ((line = in.readLine()) != null) {

            EntityUtils.consume(entity);

            httpclient.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
