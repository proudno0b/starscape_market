//package maven_directory.src.main.java.com.market.app;
package com.market.app;
public class ListingInfo {
    double price;
    long amount;

    public ListingInfo(double _price, long _amount) {
        this.price = _price;
        this.amount = _amount;
    }
    public double getPrice() {
        return price;
    }
    public long getAmount() {
        return amount;
    }
    /*public static void main(String[] args) {
        System.out.println(new ListingInfo(1,1));
    }*/
}
