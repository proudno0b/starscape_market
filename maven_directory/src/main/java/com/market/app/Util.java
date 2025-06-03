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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
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
    
    public static double getBuyOrderPrice(RealMarketItem item, String itemName) {
        try {
        JsonNode real_item = OBJECT_MAPPER.valueToTree(item);
        JsonNode buyOrders = real_item.get("fieldsMap").get("items").get(itemName).get("buy");
        System.out.println("real item " + real_item);
        System.out.println("buy orders " + buyOrders);
        ArrayList<Double> prices = new ArrayList<Double>();
        for (JsonNode listing : buyOrders) {
            prices.add(listing.get("price").asDouble());
            System.out.println("added 1 listing to prices");
        }
        int lowestindex = 0;
        for (double j : prices) {
            System.out.println("prices " + j);
        }
        for (int i=0;i<prices.size();i++) {
            System.out.println(prices.get(i));
            double currentPrice = prices.get(i);
            double lowestPrice = prices.get(lowestindex);
            if (currentPrice < lowestPrice) {
                lowestindex = i;
            }
        }
        return prices.get(lowestindex);
    } catch (Exception e) {
        System.out.println("There was a error in the util get buy order function");
        e.printStackTrace();
    }
        return -1;
    } 
 public static double getBuyOrderPrice(RealMarketItem item) {
        try {
        JsonNode real_item = OBJECT_MAPPER.valueToTree(item);
        JsonNode buyOrders = real_item.get("fieldsMap").get("items").get(item.getName()).get("buy");
        System.out.println("real item " + real_item);
        System.out.println("buy orders " + buyOrders);
        ArrayList<Double> prices = new ArrayList<Double>();
        for (JsonNode listing : buyOrders) {
            prices.add(listing.get("price").asDouble());
            System.out.println("added 1 listing to prices");
        }
        int lowestindex = 0;
        for (double j : prices) {
            System.out.println("prices " + j);
        }
        for (int i=0;i<prices.size();i++) {
            System.out.println(prices.get(i));
            double currentPrice = prices.get(i);
            double lowestPrice = prices.get(lowestindex);
            if (currentPrice < lowestPrice) {
                lowestindex = i;
            }
        }
        return prices.get(lowestindex);
    } catch (Exception e) {
        System.out.println("There was a error in the util get buy order function");
        e.printStackTrace();
    }
        return -1;
    } 
    public static double getSellOrderPrice(RealMarketItem item, String itemName) {
        try {
        JsonNode real_item = OBJECT_MAPPER.valueToTree(item);
        JsonNode sellOrders = real_item.get("fieldsMap").get("items").get(itemName).get("sell");
        System.out.println("real item " + real_item);
        System.out.println("buy orders " + sellOrders);
        ArrayList<Double> prices = new ArrayList<Double>();
        for (JsonNode listing : sellOrders) {
            prices.add(listing.get("price").asDouble());
            System.out.println("added 1 listing to prices");
        }
        int lowestindex = 0;
        for (double j : prices) {
            System.out.println("prices " + j);
        }
        for (int i=0;i<prices.size();i++) {
            System.out.println(prices.get(i));
            double currentPrice = prices.get(i);
            double lowestPrice = prices.get(lowestindex);
            if (currentPrice < lowestPrice) {
                lowestindex = i;
            }
        }
        return prices.get(lowestindex);
    } catch (Exception e) {
        System.out.println("There was a error in the util get buy order function");
        e.printStackTrace();
    }
        return -1;
    }

public static double getSellOrderPrice(RealMarketItem item) {
        try {
        JsonNode real_item = OBJECT_MAPPER.valueToTree(item);
        JsonNode sellOrders = real_item.get("fieldsMap").get("items").get(item.getName()).get("sell");
        System.out.println("real item " + real_item);
        System.out.println("buy orders " + sellOrders);
        ArrayList<Double> prices = new ArrayList<Double>();
        for (JsonNode listing : sellOrders) {
            prices.add(listing.get("price").asDouble());
            System.out.println("added 1 listing to prices");
        }
        int lowestindex = 0;
        for (double j : prices) {
            System.out.println("prices " + j);
        }
        for (int i=0;i<prices.size();i++) {
            System.out.println(prices.get(i));
            double currentPrice = prices.get(i);
            double lowestPrice = prices.get(lowestindex);
            if (currentPrice < lowestPrice) {
                lowestindex = i;
            }
        }
        return prices.get(lowestindex);
    } catch (Exception e) {
        System.out.println("There was a error in the util get buy order function");
        e.printStackTrace();
    }
        return -1;
    }
    public static void writeStatsToFile(ItemStatistics itemStats) {
        String PATH_TO_DIR = "C:\\Users\\Hoi\\Desktop\\The Archive (v3)\\code\\starscape_market\\maven_directory\\src\\main\\java\\com\\market\\app\\output";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String fileName = formatter.format(LocalDateTime.now());
        try {
        PrintWriter writer = new PrintWriter(PATH_TO_DIR+"\\output-"+fileName+".txt");
            writer.println("-----");
            writer.println(itemStats);
        writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void writeStatsToFile(ArrayList<ItemStatistics> itemStatsList) {
        String PATH_TO_DIR = "C:\\Users\\Hoi\\Desktop\\The Archive (v3)\\code\\starscape_market\\maven_directory\\src\\main\\java\\com\\market\\app\\output";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String fileName = formatter.format(LocalDateTime.now());
        try {
        PrintWriter writer = new PrintWriter(PATH_TO_DIR+"\\output-"+fileName+".txt");
        for (ItemStatistics itemStats : itemStatsList) {
            writer.println("-----");
            writer.println(itemStats);

        }
        writer.close();
        
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
