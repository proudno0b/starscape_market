//package maven_directory.src.main.java.com.market.app;
package com.market.app;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.*;
//import com.fasterxml.jackson.core.JsonFactoryBuilder;
//import com.fasterxml.jackson;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.*;
//import java.util.*;

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
        Map<String,Object> items = item.getFieldsMap();
        ArrayList<Object> itemName = new ArrayList<>(items.values());
        for (Object o : itemName) {
            System.out.println(o);
        }
        return -1;
    } 
}

