package integration;

import com.db.test.bean.Address;
import com.db.test.bean.Shop;
import com.db.test.bean.builder.AddressBuilder;
import com.db.test.config.App;
import com.db.test.request.AddShopRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
@WebIntegrationTest
public class ShopControllerIntegrationTest {

    private RestTemplate template = new TestRestTemplate();
    private Address address1 = new AddressBuilder().setNumber("280").setPostcode("EC2M 4RB").createAddress();
    private Address address2 = new AddressBuilder().setNumber("195").setPostcode("SE16 2LW").createAddress();

    @Test
    public void testAddNewShop(){

        //Given shop is not added

        //When I add a new shop address
        AddShopRequest request = buildAddShopRequest("ABC", address1);
        Shop shop = template.postForObject("http://localhost:8080/shop/add", request, Shop.class);

        // then Shop is added and I get the location of the shop back
        assertEquals(address1, shop.getAddress());
        assertEquals("ABC", shop.getShopName());
        assertNotNull(shop.getGeoLocation());
    }

    @Test
    public void testSearchNearestShopNearToFirstLocation(){
        // Given no shop currently exists

        //When I add new two new shops and query for the coordinate nearest for first shop
        AddShopRequest request2 = buildAddShopRequest("XYZ", address2);
        AddShopRequest request1 = buildAddShopRequest("ABC", address1);

        template.postForObject("http://localhost:8080/shop/add", request1, Shop.class);
        template.postForObject("http://localhost:8080/shop/add", request2, Shop.class);

        ResponseEntity<Shop> forEntity = template.getForEntity(
                "http://localhost:8080/shop/search?" + "latitude=51.5062" + "&longitude=-0.101303", Shop.class);

        // The first shop is returned
        Shop shop = forEntity.getBody();
        assertEquals(address1, shop.getAddress());
        assertEquals("ABC", shop.getShopName());
    }

    @Test
    public void testSearchNearestShopNearToSecondLocation(){
        // Given no shop currently exists

        //When I add new two new shops and query for the coordinate nearest for second shop
        AddShopRequest request1 = buildAddShopRequest("ABC", address1);
        AddShopRequest request2 = buildAddShopRequest("XYZ", address2);

        template.postForObject("http://localhost:8080/shop/add", request1, Shop.class);
        template.postForObject("http://localhost:8080/shop/add", request2, Shop.class);

        ResponseEntity<Shop> forEntity = template.getForEntity(
                "http://localhost:8080/shop/search?" + "latitude=51.5066" + "&longitude=-0.0261", Shop.class);
        Shop shop = forEntity.getBody();

        // The second shop is returned
        assertEquals(address2, shop.getAddress());
        assertEquals("XYZ", shop.getShopName());
    }

    private AddShopRequest buildAddShopRequest(String name, Address address) {

        AddShopRequest request = new AddShopRequest();
        request.setShopName(name);
        request.setAddress(address);

        return request;
    }

}
