package com.db.test.service;

import com.db.test.bean.GeoLocation;
import com.db.test.bean.Shop;
import com.db.test.helper.NearestLocationFinder;
import com.db.test.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NearestShopService {

    private ShopRepository shopRepository;
    private NearestLocationFinder locationFinder;

    public NearestShopService(){};

    @Autowired
    public NearestShopService(ShopRepository shopRepository, NearestLocationFinder locationFinder) {
        this.shopRepository = shopRepository;
        this.locationFinder = locationFinder;
    }

    public Shop find(GeoLocation location) {

        if(shopRepository.getAllLocation().isEmpty()) return null;

        GeoLocation geoLocation = locationFinder.search(location, shopRepository.getAllLocation());

        return shopRepository.getShop(geoLocation);
    }



}
