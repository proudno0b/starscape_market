//package maven_directory.src.main.java.com.market.app;
package com.market.app;

class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");
        System.out.println("wtf");
        RequestSender sender = new RequestSender();
        MarketItem2 sigma = Util.readJsonFile("/TestHorizon.json");
        System.out.println(sigma);
        //sender.FetchItemData("Horizon");
    }
}