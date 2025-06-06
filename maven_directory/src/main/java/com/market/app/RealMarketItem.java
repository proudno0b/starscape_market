package com.market.app;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
// using method taken from https://stackoverflow.com/questions/72057546/how-to-deserialize-this-dynamic-keys-json-to-java-custom-pojo-using-jackson
public class RealMarketItem {
        private Map<String, JsonNode> dynamicKeys = new HashMap<>();
        private String name; //necessary to allow fetching of dynamic json key which is the name of the item
    
    public void setName(String n) {
        this.name = n;
    }
    public String getName() {
        
        return name;
    }

    @JsonAnySetter
    public void setUnknownFields(String name, JsonNode value) {
        // JSON Anysetter from com.fasterxml.jackson.databind that dynamically maps JSON values to a generic java object 
        dynamicKeys.put(name, value);
    }
    
    public Map<String, JsonNode> getFieldsMap() {
        return dynamicKeys; // returns dynamic keys which is basically a POJO implementation of a JSON as Map<String,Object>
    }
    public String toString() {
        //my own code, prints out key value pairs with an "=" separating the two
        String out = "";
        for (String s : dynamicKeys.keySet()) {
                out += (s + " = " + dynamicKeys.get(s)) ; 
        }
        return out;
    }
}