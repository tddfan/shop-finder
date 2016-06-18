package com.db.test.repository;

import com.db.test.bean.GeoLocation;
import com.db.test.bean.Shop;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ShopRepository {
    private Map<GeoLocation, Shop> repository = new ConcurrentHashMap<>();

    public void addShop(Shop shop) {
        repository.put(shop.getGeoLocation(), shop);
    }

    public Set<GeoLocation> getAllLocation() {
        return repository.keySet();
    }

    public Shop getShop(GeoLocation geoLocation) {
        return repository.get(geoLocation);
    }

    public int size() {
        return repository.size();
    }

}
