package com.market.app;

import com.market.app.MarketItem.ListingInfo.VendorInfo;

public record MarketOrder(
    double price,
    int amount,
    VendorInfo v
) {

}
