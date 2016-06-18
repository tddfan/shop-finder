package com.db.test.bean;

import java.util.Objects;

public class Shop {

    private String shopName;
    private Address address;
    private GeoLocation geoLocation;

    public Shop(){};

    public Shop(String shopName, Address address, GeoLocation geoLocation) {
        this.shopName = shopName;
        this.address = address;
        this.geoLocation = geoLocation;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getShopName() {
        return shopName;
    }

    public Address getAddress() {
        return address;
    }

    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return Objects.equals(shopName, shop.shopName) &&
                Objects.equals(address, shop.address) &&
                Objects.equals(geoLocation, shop.geoLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shopName, address, geoLocation);
    }
}
