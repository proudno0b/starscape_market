package com.market.app;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties
public record MarketItem2 (
    Horizon iteminfo
) {
    public record Horizon (
        Item item
    ) {
    public record Item (
    String name,
    Integer _id,
    String time_scanned,
    List<ListingInfo> buy,
    List<ListingInfo> sell
    ) {
    public record ListingInfo(
        double price,
        Integer amount,
        VendorInfo vendor
    ) {
        public record VendorInfo(
            Integer _id,
            String name,
            String displayName
        ) {}
    } 
}
    }
}
