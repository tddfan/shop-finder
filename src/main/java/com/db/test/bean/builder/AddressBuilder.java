package com.db.test.bean.builder;

import com.db.test.bean.Address;

public class AddressBuilder {
    private String number;
    private String postcode;

    public AddressBuilder setNumber(String number) {
        this.number = number;
        return this;
    }

    public AddressBuilder setPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public Address createAddress() {
        return new Address(number, postcode);
    }
}