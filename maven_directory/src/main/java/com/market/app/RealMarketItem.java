package com.market.app;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
// using method taken from https://stackoverflow.com/questions/72057546/how-to-deserialize-this-dynamic-keys-json-to-java-custom-pojo-using-jackson
public class RealMarketItem {
        private Map<String, JsonNode> dynamicKeys = new HashMap<>();
        private String name;
    
    public void setName(String n) {
        this.name = n;
    }
    public String getName() {
        return name;
    }
    @JsonAnySetter
    public void setUnknownFields(String name, JsonNode value) {
        dynamicKeys.put(name, value);
    }
    
    public Map<String, JsonNode> getFieldsMap() {
        return dynamicKeys;
    }
    public String toString() {
        String out = "";
        for (String s : dynamicKeys.keySet()) {
                out += (s + " = " + dynamicKeys.get(s)) ;
        }
        return out;
    }
}