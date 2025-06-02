//package maven_directory.src.main.java.com.market.app;
package com.market.app;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.*;
//import com.fasterxml.jackson.core.JsonFactoryBuilder;
//import com.fasterxml.jackson;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.*;
//import java.util.*;

import org.apache.commons.beanutils.BeanUtils;

public class Util {
//    //adapted from medium article

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);;
    
    public static Map<String,MarketItem> toItem(InputStream inputStream) {
        try {
            //OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            //return OBJECT_MAPPER.readValue(inputStream, MarketItem.class);

            // input stream is HttpResponse body with BodyHandlers.ofInputStream
            Map<String,MarketItem> mapper = 
            OBJECT_MAPPER.readValue(inputStream, new TypeReference<Map<String,MarketItem>>() {});
            return mapper;
        } catch (IOException e) {
            System.out.println("there was an IO exception in util to list method");
            e.printStackTrace();
        }
        return null;
    }
    public static RealMarketItem toRealMarketItem(InputStream inputStream) {
        try {
            return OBJECT_MAPPER.readValue(inputStream,RealMarketItem.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static MarketItem readJsonFile(String s) {
        
        try {
            File file = new File(".");
            for(String fileNames : file.list()) System.out.println(fileNames);
            File f = new File("starscape_market\\maven_directory\\src\\main\\java\\com\\market\\app\\TestHorizon.json");
            return OBJECT_MAPPER.readValue(f,MarketItem.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    public static List<Object> toList(InputStream inputStream) {
        try {
            return OBJECT_MAPPER.readValue(inputStream, new TypeReference<>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static double getBuyOrderPrice(RealMarketItem item) {
        try {
        JsonNode real_item = OBJECT_MAPPER.valueToTree(item);
        JsonNode buyOrders = real_item;
        System.out.println("real item " + real_item);
        ArrayList<JsonNode> prices = new ArrayList<JsonNode>();
        for (JsonNode listing : buyOrders) {
            prices.add(listing.get(0));
            System.out.println("added 1 listing to prices");
        }
        int lowestindex = 0;
        for (JsonNode j : prices) {
            System.out.println("prices " + j);
        }
        for (int i=0;i<prices.size();i++) {
            System.out.println(prices.get(i));
            List<MarketOrder> currentPrice = nodeToOrder(prices.get(i));
            List<MarketOrder> lowestPrice = nodeToOrder(prices.get(lowestindex));
            if (currentPrice.get(0).price() < lowestPrice.get(0).price()) {
                lowestindex = i;
            }
        }
        return nodeToOrder(prices.get(lowestindex)).get(0).price();
    } catch (Exception e) {
        System.out.println("There was a error in the util get buy order function");
        e.printStackTrace();
    }
        return -1;
    } 

public static List<MarketOrder> nodeToOrder(JsonNode node) {
    TypeReference<List<MarketOrder>> orderReference = new TypeReference<List<MarketOrder>>() {};
    try {
    return OBJECT_MAPPER.readValue(node.traverse(),orderReference);
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;

} 
}
