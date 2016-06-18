package com.db.test.bean.builder;

import com.db.test.bean.Address;
import com.db.test.bean.GeoLocation;
import com.db.test.bean.Shop;

public class ShopBuilder {
    private String shopName;
    private Address address;
    private GeoLocation geoLocation;

    public ShopBuilder setShopName(String shopName) {
        this.shopName = shopName;
        return this;
    }

    public ShopBuilder setAddress(Address address) {
        this.address = address;
        return this;
    }

    public ShopBuilder setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
        return this;
    }

    public Shop createShop() {
        return new Shop(shopName, address, geoLocation);
    }
}