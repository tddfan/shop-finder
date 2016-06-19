package com.db.test.helper;

import com.db.test.bean.GeoLocation;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class NearestLocationFinder {

    public GeoLocation search(GeoLocation searchFor, Set<GeoLocation> searchFrom) {

        GeoLocation nearestGeoLocation = null;
        double lastMinDistance = 0;

        for (GeoLocation geoLocation : searchFrom) {
            double distance = distFrom(geoLocation, searchFor);
            if(nearestGeoLocation == null ||  distance < lastMinDistance) {
                nearestGeoLocation = geoLocation;
                lastMinDistance = distance;
            }
        }
        return nearestGeoLocation;
    }

    // Have taken this code from Internet
    private double distFrom(GeoLocation geoLocation1, GeoLocation geoLocation2) {

        double radLatitude = Math.toRadians(geoLocation2.getLatitude() - geoLocation1.getLatitude());
        double radLongitude = Math.toRadians(geoLocation2.getLongitude() - geoLocation1.getLongitude());

        double a = Math.sin(radLatitude/2) * Math.sin(radLatitude/2) +
                Math.cos(Math.toRadians(geoLocation1.getLatitude())) * Math.cos(Math.toRadians(geoLocation2.getLatitude())) *
                       Math.sin(radLongitude/2) * Math.sin(radLongitude/2);

        return  Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

    }
}
