package com.db.test.controller;



import com.db.test.request.AddShopRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class ShopController {

    @RequestMapping(value="/shop/add", method= POST)
    public String addShop(@RequestBody AddShopRequest request) {
        return null;
    }

}
