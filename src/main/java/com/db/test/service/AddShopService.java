package com.db.test.service;

import com.db.test.bean.Address;
import com.db.test.bean.GeoLocation;
import com.db.test.bean.Shop;
import com.db.test.bean.builder.ShopBuilder;
import com.db.test.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddShopService {

    private GeoLocationService geoLocationService;
    private ShopRepository shopRepository;

    @Autowired
    public AddShopService(GeoLocationService geoLocationService, ShopRepository shopRepository) {
        this.geoLocationService = geoLocationService;
        this.shopRepository = shopRepository;
    }

    public Shop save(String shopName, Address address) {

        GeoLocation geoLocation = geoLocationService.findLocation(address);

        Shop shop = new ShopBuilder().setShopName(shopName).setAddress(address).setGeoLocation(geoLocation).createShop();

        shopRepository.addShop(shop);

        return shop;

    }
}
