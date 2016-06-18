
package com.db.test.service;


import com.db.test.bean.Address;
import com.db.test.bean.GeoLocation;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import org.springframework.stereotype.Service;

@Service
public class GeoLocationService {

    public GeoLocation findLocation(Address address) {
        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyD0kxhy0wl5Cdd6vAsHD7rvBd2H0V6fRTc");
        GeocodingResult[] results = new GeocodingResult[0];

        try {
            results = GeocodingApi.geocode(context,
                    address.getNumber() + ", " + address.getPostcode() + ", Great Britain").await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new GeoLocation(results[0].geometry.location.lat, results[0].geometry.location.lng);

    }
}
