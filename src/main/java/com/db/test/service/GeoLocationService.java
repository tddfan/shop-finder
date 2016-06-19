
package com.db.test.service;


import com.db.test.bean.Address;
import com.db.test.bean.GeoLocation;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GeoLocationService {

    @Value("${google.maps.api.key}")
    private String apiKey;

    public GeoLocation findLocation(Address address) {

        System.out.println("Key =" + apiKey);
        GeoApiContext context = new GeoApiContext().setApiKey(apiKey);
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
