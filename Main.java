//package starscape_market;
import java.net.*;
import java.net.http.*;
import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");
        System.out.println("wtf");
        RequestSender sender = new RequestSender();
        HttpResponse<InputStream> output = sender.FetchItemData("Horizon");
        //sender.ItemToFile("Horizon");
        
    }
}