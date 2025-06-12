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

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    //fail on unknown properties = false stops objectmapper from throwing an exception when there are unset properties
    public static RealMarketItem toRealMarketItem(InputStream inputStream) {
        try {
            return OBJECT_MAPPER.readValue(inputStream,RealMarketItem.class); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // to ensure it compiles
    }
    public static JsonNode readJsonFile(String s) {
        String PATH_TO_DIR = new Secrets().getDirectory();
        
        try {
            File f = new File(PATH_TO_DIR+"\\"+s); //relative path to file to be scanned, allows loading from file
            return OBJECT_MAPPER.readValue(f,JsonNode.class); 
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
    public static JsonNode toJsonNode(InputStream inputStream) {
        try {
            return OBJECT_MAPPER.readValue(inputStream,JsonNode.class);
            
        } catch (Exception e) {
            System.out.println("There was an error in the toJsonNode method in Util.java: " +e.getMessage());
        }
        return null;
    }
    public static double getBuyOrderPrice(RealMarketItem item, String itemName) {
        try {
        JsonNode real_item = OBJECT_MAPPER.valueToTree(item); //reads RealMarketItem as JsonNode
        JsonNode buyOrders = real_item.get("fieldsMap").get("items").get(itemName).get("buy");
        if (!buyOrders.isEmpty()) {
        System.out.println("real item " + real_item);
        System.out.println("buy orders " + buyOrders);
        ArrayList<Double> prices = new ArrayList<Double>();
        for (JsonNode listing : buyOrders) {
            prices.add(listing.get("price").asDouble());
            System.out.println("added 1 listing to prices");
        }
        int highestindex = 0;
        for (double j : prices) {
            System.out.println("prices " + j);
        }
        for (int i=0;i<prices.size();i++) {
            System.out.println(prices.get(i));
            double currentPrice = prices.get(i);
            double highestPrice = prices.get(highestindex);
            if (currentPrice > highestPrice) {
                highestindex = i;
            }
        }
        return (prices.size() > 0) ? prices.get(highestindex) : null;
    } else {
        System.out.println("buy order for " +itemName + "is empty");
        return -1;
    }
    } catch (Exception e) {
        System.out.println("There was a error in the util get buy order function");
        e.printStackTrace();
    }
        return -1;
    } 
 public static double getBuyOrderPrice(RealMarketItem item) {
    return getBuyOrderPrice(item,item.getName());
 }
 public static double getSellOrderPrice(RealMarketItem item) {
    return getSellOrderPrice(item,item.getName());
 }
    public static double getSellOrderPrice(RealMarketItem item, String itemName) {
        try {
        JsonNode real_item = OBJECT_MAPPER.valueToTree(item);
        JsonNode sellOrders = real_item.get("fieldsMap").get("items").get(itemName).get("sell");
        System.out.println("real item " + real_item);
        System.out.println("buy orders " + sellOrders);
        if (!sellOrders.isEmpty()) {
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
        return (prices.size() > 0) ? prices.get(lowestindex) : null;
    } else {
        System.out.println("item " + itemName + " is empty");
        return -1;
    }
    } catch (Exception e) {
        System.out.println("There was a error in the util get buy order function");
        e.printStackTrace();
    }
        return -1;
    }
public static double getSellOrderPrice(JsonNode real_item,String itemName) {
    JsonNode sellOrders = real_item.get("items").get(itemName).get("sell");
    try {
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
        return (prices.size() > 0) ? prices.get(lowestindex) : null;
    } catch (Exception e) {
        System.out.println("There was a error in the util get sell order jsonnode order function");
        e.printStackTrace();
    }
        return -1;
    }
    public static double getBuyOrderPrice(JsonNode real_item,String itemName) {
    JsonNode buyOrders = real_item.get("items").get(itemName).get("buy");
    try {
        System.out.println("real item " + real_item);
        System.out.println("buy orders " + buyOrders);
        ArrayList<Double> prices = new ArrayList<Double>();
        for (JsonNode listing : buyOrders) {
            prices.add(listing.get("price").asDouble());
            System.out.println("added 1 listing to prices");
        }
        int highestindex = 0;
        for (double j : prices) {
            System.out.println("prices " + j);
        }
        for (int i=0;i<prices.size();i++) {
            System.out.println(prices.get(i));
            double currentPrice = prices.get(i);
            double highestPrice = prices.get(highestindex);
            if (currentPrice > highestPrice) {
                highestindex = i;
            }
        }
        return (prices.size() > 0) ? prices.get(highestindex) : null;
    } catch (Exception e) {
        System.out.println("There was a error in the util get buy order jsonnode function");
        e.printStackTrace();
    }
        return -1;
    }

    public static void writeStatsToFile(ItemStatistics itemStats) {
        String PATH_TO_DIR = new Secrets().getDirectory()+"\\output";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String fileName = formatter.format(LocalDateTime.now());
        try {
        PrintWriter writer = new PrintWriter(PATH_TO_DIR+"\\output-"+fileName+".txt");
            writer.println(itemStats);
            writer.println("-----");
        writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }

    public static void writeStatsToFile(ItemStatistics itemStats, String PATH_TO_DIR) {
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
        String PATH_TO_DIR = new Secrets().getDirectory()+"\\output";
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
    public static FlatItemStatistics toFlat(ItemStatistics itemStats) {
        return new FlatItemStatistics(itemStats);
    }
}
