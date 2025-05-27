//package maven_directory.src.main.java.com.market.app;
package com.market.app;
import java.net.http.*;
//import java.util.*;
import java.io.*;
//import org.json.*;
//import java.net.http.URI;
import java.net.URI;

public class RequestSender {
    //boolean exists;
    public RequestSender() {
    }
    public MarketItem FetchItemData(String item) {
        String api_key = new Secrets().getAPIKey();

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(String.format("https://api.v-io.info/v1/market/latest?items=%s",item)))
            .header("x-api-key",api_key)
            .GET()
            .build();
            HttpResponse<InputStream> response = client.send(request,HttpResponse.BodyHandlers.ofInputStream());
            if (response.statusCode() == 200) {
                return Util.toItem(response.body());
            } else {
                System.out.println("Request was bad " + response.statusCode());
            }
            System.out.println("request " + request);
            System.out.println("response " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    public void FetchMarketData() {

    }

}
