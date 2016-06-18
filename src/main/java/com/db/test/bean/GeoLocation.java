package com.db.test.bean;


import java.util.Objects;

public class GeoLocation {

    private double latitude;
    private double longitude;

    public GeoLocation(){};

    public GeoLocation(double lat, double lng) {
        this.latitude = lat;
        this.longitude = lng;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeoLocation geoLocation = (GeoLocation) o;
        return Objects.equals(latitude, geoLocation.latitude) &&
                Objects.equals(longitude, geoLocation.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }
}
