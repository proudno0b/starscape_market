package com.market.app;

public class ItemStatistics {
    private String itemName;
    private double profitMargin;
    private double percentMargin;
    public ItemStatistics(String _itemName,double _profitMargin,double _percentMargin) {
        this.itemName = _itemName;
        this.profitMargin = _profitMargin;
        this.percentMargin = _percentMargin;
    }
    public String toString() {
        return String.format("Item Name: %s%n Buy/Sell price spread: %s%n Percentage profit margin: %.2f%%",itemName,profitMargin,percentMargin);
    }
}
