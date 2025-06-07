package com.market.app;

//comparable taken from official documentation
public class ItemStatistics implements Comparable<ItemStatistics> {
    //POJO 
    private String itemName;
    private double profitMargin;
    private double percentMargin;
    private double buyPrice;
    private double sellPrice;
    public ItemStatistics(String _itemName,double buyPrice,double sellPrice) {
        this.itemName = _itemName;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.profitMargin = sellPrice - buyPrice;
        this.percentMargin = 100*this.profitMargin / (buyPrice);
    }
    @Override
    public int compareTo(ItemStatistics other) {
        if (this.percentMargin < other.percentMargin) {
            return -1;
        } else if (this.percentMargin > other.percentMargin) {
            return 1;
        } else {
            return 0;
        }

    }
    public String toString() {
        return String.format("Item Name: %s%n Buy price: %s%n Sell price: %s%n Profit Margin(credits): %s%.2f Percentage profit margin: %.2f%%",itemName,buyPrice,sellPrice,profitMargin,percentMargin);
    }
}