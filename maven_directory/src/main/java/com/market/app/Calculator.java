package com.market.app;

public class Calculator {
    public static ItemStatistics calculateMarketSpread(RealMarketItem r) {
        double lowest_buy = Util.getBuyOrderPrice(r);
        double lowest_sell = Util.getSellOrderPrice(r);
        ItemStatistics item = new ItemStatistics(Math.abs(lowest_sell - lowest_buy),(lowest_sell - lowest_buy/lowest_buy+lowest_sell));
        return item;
    }
}
