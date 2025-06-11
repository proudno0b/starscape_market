//package maven_directory.src.main.java.com.market.app;
package com.market.app;
import java.io.InputStream;
import java.io.InputStream;
//import org.json.*;
//import java.net.http.URI;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;
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
            String uri = "https://api.v-io.info/v1/market/latest?items=";
            HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(uri + URLEncoder.encode(item))) // fetches actual item
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
        fetchMarketData(false,-1,true);

    }
    public void fetchMarketData(boolean usePercent) {
        fetchMarketData(false,10,usePercent);
    }
    public RealMarketItem goodFetchMarketData(int amount, boolean useCache) {
        // good ver. try to use this
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
                    JsonNode node = Util.toJsonNode(response.body());
                    int commaCount = 0;
                    for (JsonNode element : node) {
                        commaCount++;
                    }
                    StringBuilder builder = new StringBuilder(node.size() + commaCount);
                    for (JsonNode element : node) {
                        builder.append(element.asText());
                        builder.append(",");
                    }
                    HttpRequest marketFetcher = HttpRequest.newBuilder()
                    .uri(new URI("https://api.v-io.info/v1/market/items")) // fetches list of possible items
                    .header("x-api-key",API_KEY) //supplies authentication
                    .GET() //specifies request as a GET request
                    .build(); //completes HttpRequest
                    HttpResponse<InputStream> fetchedResponse = client.send(marketFetcher,HttpResponse.BodyHandlers.ofInputStream());
                    RealMarketItem market = Util.toRealMarketItem(fetchedResponse.body());
                    return market;

                } else {
                    System.out.println("response was bad " + response.statusCode());
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
    public void fetchMarketData(boolean useCache, int amount, boolean usePercent) {
        /// WARNING: SLOW due to trying to avoid http response 429 too many requests, highly recommend using cached data when that is implemented.
        /// will be deprecated
        /// latest run took 13.91667 minutes :skull:
        long before = System.currentTimeMillis();

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
                    ItemStatistics numItems = new ItemStatistics("number of items available",1,node.size());
                    ArrayList<ItemStatistics> allItems = new ArrayList<>();
                    for (int i = 0; i<node.size();i++) {
                        // fetches list of all possible items in market
                        String item = node.get(i).asText();
                        
                        RealMarketItem r = this.fetchRealMarketItem(item);
                        // adds each fetched item to a RealMarketItem object, then converts it into an ItemStatistics and adds it to an ArrayList.
                        allItems.add(Calculator.calculateMarketSpread(r));
                        Thread.sleep(1000);

                    }

                    // sorts list of all possible items in reverse order to display highest % profit margins first
                    Collections.sort(allItems,Collections.reverseOrder());
                    Calculator.writeDataToCache(allItems); // caches fetched data for future use
                    ArrayList<ItemStatistics> statsToOutput = new ArrayList<>();
                    int finalAmount = (amount == -1) ? node.size() : amount;
                    for (int i=0;i<finalAmount;i++) {
                        statsToOutput.add(allItems.get(i));
                        //transcribes the N highest margin items to a new list
                    }
                    Util.writeStatsToFile(statsToOutput); // writes output of the N highest margin items to file
                    System.out.println("successfully fetched market data and wrote it to file");
                    
                } else {
                    System.out.println("Request was bad " + response.statusCode());
                    System.out.println(response.headers());
                    System.out.println("Trying again from cached data...");
                    //fetchMarketData(useCache = true);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        System.out.println("Market Data fetch completed with time taken:" + ((System.currentTimeMillis()-before)/1000));
    }
    public void fetchResourcePrices() {

    }
    public void fetchDataFromList(ArrayList<String> itemList) {
        ArrayList<ItemStatistics> outputList = new ArrayList<>();
        for (String item : itemList) {
            RealMarketItem real = fetchRealMarketItem(item);
            ItemStatistics output = Calculator.calculateMarketSpread(real);
            outputList.add(output);
        }
        Util.writeStatsToFile(outputList);
    }
}
