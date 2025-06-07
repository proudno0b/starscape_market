package com.market.app;

//comparable taken from official documentation
public class ItemStatistics implements Comparable<ItemStatistics> {
    //POJO 
    private String itemName;
    protected double profitMargin;
    protected double percentMargin;
    private double buyPrice;
    private double sellPrice;
    public ItemStatistics(String _itemName,double buyPrice,double sellPrice) {
        this.itemName = _itemName;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.profitMargin = sellPrice - buyPrice;
        this.percentMargin = (buyPrice > 0 && sellPrice > 0) ? 100*this.profitMargin / (buyPrice) : 0;
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
        return String.format("Item Name: %s%n Buy price: %s%n Sell price: %s%n Profit Margin(credits): %.2f%n Percentage profit margin: %.2f%%",itemName,buyPrice,sellPrice,profitMargin,percentMargin);
    }
}