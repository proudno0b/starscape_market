//package maven_directory.src.main.java.com.market.app;
package com.market.app;
import java.util.*;
import java.io.*;
public class MarketItem {
    String name;
    int id;
    String time_scanned;
    ArrayList<ListingInfo> buy;
    ArrayList<ListingInfo> sell;

   /*  String name,
    int id,
    String time_scanned,
    ArrayList<String> buy, // price amount vendor id name display name
    ArrayList<String> sell, // same as buy*/
    public MarketItem(String _name, int _id, String _time_scanned) {
        this.name = _name;
        this.id = _id;
        this.time_scanned = _time_scanned;
        this.buy = new ArrayList<>();
        this.sell = new ArrayList<>();
    }

}
    

