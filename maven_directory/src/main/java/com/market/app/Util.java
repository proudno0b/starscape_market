//package maven_directory.src.main.java.com.market.app;
package com.market.app;
import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.*;
//import com.fasterxml.jackson.core.JsonFactoryBuilder;
//import com.fasterxml.jackson;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;

public class Util {
//    //adapted from medium article

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static List<> toList(InputStream inputStream) {
        try {
            return OBJECT_MAPPER.readValue(inputStream, new TypeReference<>());
        } catch (IOException e) {
            System.out.println("there was an IO exception in util to list method");
            e.printStackTrace();
        }
        return null;
    }
    public static void test() {

    }
}
