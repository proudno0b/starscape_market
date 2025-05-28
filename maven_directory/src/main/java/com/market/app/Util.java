//package maven_directory.src.main.java.com.market.app;
package com.market.app;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.*;
//import com.fasterxml.jackson.core.JsonFactoryBuilder;
//import com.fasterxml.jackson;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
//import java.util.*;

public class Util {
//    //adapted from medium article

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    
    public static MarketItem2 toItem(InputStream inputStream) {
        try {
            //OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return OBJECT_MAPPER.readValue(inputStream, MarketItem2.class);
        } catch (IOException e) {
            System.out.println("there was an IO exception in util to list method");
            e.printStackTrace();
        }
        return null;
    }
    public static MarketItem2 readJsonFile(String s) {
        
        try {
        File f = new File("TestHorizon.json");
        return OBJECT_MAPPER.readValue(f,MarketItem2.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
} 

