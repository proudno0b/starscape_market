//package maven_directory.src.main.java.com.market.app;
package com.market.app;
import java.io.InputStream;
//import org.json.*;
//import java.net.http.URI;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;

public class RequestSender {
    //constructor not necessary as there's no
    private String API_KEY;
    public RequestSender() {
        this.API_KEY = new Secrets().getAPIKey();
    }
    public RequestSender(String key) {
        this.API_KEY = key;
    }
    public RealMarketItem fetchRealMarketItem(String item) {
        
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(String.format("https://api.v-io.info/v1/market/latest?items=%s",item))) // fetches actual item
            .header("x-api-key",API_KEY) //supplies authentication
            .GET() //specifies request as a GET request
            .build(); //completes HttpRequest
            HttpResponse<InputStream> response = client.send(request,HttpResponse.BodyHandlers.ofInputStream()); 
            // takes httpresponse with input stream handler as jackson databind's object mapper requires an InputStream as an argument
            System.out.println("request " + request);
            System.out.println("response " + response);
            if (response.statusCode() == 200) {
                RealMarketItem output = Util.toRealMarketItem(response.body()); //returns mapped JSON as a RealMarketItem Object
                output.setName(item); // required for easy fetching of dynamic json attr. which is the item's name
                return output;
            } else {
                System.out.println("Request was bad " + response.statusCode());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    public void fetchMarketData() {
        fetchMarketData(false);

    }

    public void fetchMarketData(boolean useCache) {
        if (useCache) {
            System.out.println("successfully wrote data from cache");
        } else {
            try {
                HttpClient client = HttpClient.newHttpClient();

                HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.v-io.info/v1/market/items")) // fetches list of possible items
                .header("x-api-key",API_KEY) //supplies authentication
                .GET() //specifies request as a GET request
                .build(); //completes HttpRequest
                HttpResponse<InputStream> response = client.send(request,HttpResponse.BodyHandlers.ofInputStream()); 
                if (response.statusCode() == 200) {
                    //do something
                    JsonNode node = Util.toJsonNode(response.body());
                    System.out.println(node);
                    System.out.println("is node array? " + node.isArray());
                    ArrayList<ItemStatistics> allItems = new ArrayList<>();
                    for (int i = 0; i<node.size();i++) {
                        String item = node.get(i).asText();
                        RealMarketItem r = this.fetchRealMarketItem(item);
                        allItems.add(Calculator.calculateMarketSpread(r));
                    }
                    Calculator.writeDataToCache(allItems);
                    Util.writeStatsToFile(allItems);
                    System.out.println("successfully fetched market data and wrote it to file");
                } else {
                    System.out.println("Request was bad " + response.statusCode());
                    System.out.println("Trying again from cached data...");
                    fetchMarketData(true);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
