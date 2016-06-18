package com.db.test.request;

import com.db.test.bean.Address;

public class AddShopRequest {
    private String shopName;
    private Address address;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
