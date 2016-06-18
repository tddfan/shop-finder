package com.db.test.bean;

import java.util.Objects;

public class Address {
    private String number;
    private String postcode;

    public Address(){};

    public Address(String number, String postcode) {
        this.number = number;
        this.postcode = postcode;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getNumber() {
        return number;
    }

    public String getPostcode() {
        return postcode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "number='" + number + '\'' +
                ", postcode='" + postcode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(number, address.number) &&
                Objects.equals(postcode, address.postcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, postcode);
    }
}
