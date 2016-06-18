package com.db.test.controller;



import com.db.test.bean.Shop;
import com.db.test.request.AddShopRequest;
import com.db.test.service.AddShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class ShopController {

    private AddShopService addShopService;

    @Autowired
    public ShopController(AddShopService addShopService) {
        this.addShopService = addShopService;
    }

    @RequestMapping(value="/shop/add", method= POST)
    public Shop addShop(@RequestBody AddShopRequest request) {
        return addShopService.save(request.getShopName(), request.getAddress());
    }

}
