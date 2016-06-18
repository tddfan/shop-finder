package com.db.test.bean.builder;

import com.db.test.bean.GeoLocation;

public class GeoLocationBuilder {
    private double lat;
    private double lng;

    public GeoLocationBuilder setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public GeoLocationBuilder setLng(double lng) {
        this.lng = lng;
        return this;
    }

    public GeoLocation createGeoLocation() {
        return new GeoLocation(lat, lng);
    }
}