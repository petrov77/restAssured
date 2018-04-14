package RestServicesTests;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.apache.http.protocol.HTTP.USER_AGENT;

public class OrdinaryPostmanCalls {
    public static JSONObject executePostRequest(String url) {

        PostmanParams postmanParams = new PostmanParams();
        postmanParams.data.put("slagam KEY", "VALUE slagam malko tuka taka az");
        postmanParams.storage = "slagam malko STRING";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpEntity payload = new StringEntity(postmanParams.toJsonString(), ContentType.create("application/json", "UTF-8"));
            HttpPost post = new HttpPost(url);
            post.setEntity(payload);

            try (CloseableHttpResponse response = httpClient.execute(post)) {
                String result = EntityUtils.toString(response.getEntity());
                System.out.println("Response is: " + result);

                if ((response.getStatusLine().getStatusCode() != 200)
                        || (!response.getEntity().getContentType().getValue().equals("application/json; charset=utf-8"))) {
                    throw new RuntimeException(result);
                }

                System.out.println("Received a good response");
                return new JSONObject(result);
            }
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to communicate with the API at %s", url), e);
        }
    }


    public static void sendGet(String url) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //Request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println(response.toString());
    }
}
