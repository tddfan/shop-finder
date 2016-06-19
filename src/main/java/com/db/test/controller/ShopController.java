package com.db.test.controller;

import com.db.test.bean.Shop;
import com.db.test.bean.builder.GeoLocationBuilder;
import com.db.test.request.AddShopRequest;
import com.db.test.service.AddShopService;
import com.db.test.service.NearestShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class ShopController {

    private AddShopService addShopService;
    private NearestShopService nearestShopService;

    public ShopController(){};

    @Autowired
    public ShopController(AddShopService addShopService, NearestShopService nearestShopService) {
        this.addShopService = addShopService;
        this.nearestShopService = nearestShopService;
    }

    @RequestMapping(value="/shop/add", method= POST)
    public Shop addShop(@RequestBody AddShopRequest request) {
        return addShopService.save(request.getShopName(), request.getAddress());
    }

    @RequestMapping(value="/shop/search", method= GET)
    public Shop searchNearestShop(@RequestParam(value="latitude") String latitude,
                                  @RequestParam(value="longitude") String longitude) {

       return nearestShopService.find(
               new GeoLocationBuilder()
                       .setLat(Double.valueOf(latitude))
                       .setLng(Double.valueOf(longitude))
                       .createGeoLocation());
    }

}
