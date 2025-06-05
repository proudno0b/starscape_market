package com.market.app;
import com.fasterxml.jackson.databind.*;
public class Calculator {
    public static ItemStatistics calculateMarketSpread(RealMarketItem r) {
        double lowest_buy = Util.getBuyOrderPrice(r);
        double lowest_sell = Util.getSellOrderPrice(r);
        ItemStatistics item = new ItemStatistics(r.getName(),lowest_buy,lowest_sell);
        return item;
    }
    public static ItemStatistics calculateMarketSpread(JsonNode J, String itemName) {
        double lowest_buy = Util.getBuyOrderPrice(J,itemName);
        double lowest_sell = Util.getSellOrderPrice(J,itemName);
        ItemStatistics item = new ItemStatistics(itemName, lowest_buy, lowest_sell);
        return item;
    }
}
