package com.market.app;
import java.util.*;
public record MarketItem (
    Items item
)  { public record Items (
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