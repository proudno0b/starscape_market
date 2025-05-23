//package starscape_market;
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
    ArrayList<String> buy, // price amount vendor id name displayname
    ArrayList<String> sell, // same as buy*/
    public MarketItem(String _name, int _id, String _time_scanned) {
        this.name = _name;
        this.id = _id;
        this.time_scanned = _time_scanned;
        this.buy = new ArrayList<ListingInfo>();
        this.sell = new ArrayList<ListingInfo>();
    }

}
    

