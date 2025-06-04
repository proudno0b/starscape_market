package com.market.app;
import java.util.*;
import com.fasterxml.jackson.databind.*;
class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");
        System.out.println("wtf");
        RequestSender sender = new RequestSender();
        //MarketItem2 sigma = Util.readJsonFile("/TestHorizon.json");
        //System.out.println(sigma);
        //RealMarketItem e = sender.fetchRealMarketItem("Horizon");
        System.out.println(" ---");
        //System.out.println(e);
        System.out.println(" ---");
        //System.out.println("lowest price " + Util.getSellOrderPrice(e,"Horizon"));
        //Util.writeStatsToFile(Calculator.calculateMarketSpread(e));
        JsonNode J = Util.readJsonFile("TestHorizon.json");
        System.out.println(J);
        System.out.println(J.get("items").get("Horizon").get("buy").get(0).get("vendor").get("displayName").asText());

    }
}
