package unit.controller;

import com.db.test.bean.Address;
import com.db.test.bean.GeoLocation;
import com.db.test.bean.Shop;
import com.db.test.bean.builder.AddressBuilder;
import com.db.test.bean.builder.GeoLocationBuilder;
import com.db.test.bean.builder.ShopBuilder;
import com.db.test.controller.ShopController;
import com.db.test.request.AddShopRequest;
import com.db.test.service.AddShopService;
import com.db.test.service.NearestShopService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class ShopControllerTest {

    private MockMvc mvc;

    @Mock private AddShopService addShopService;
    @Mock private NearestShopService nearestShopService;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new ShopController(addShopService, nearestShopService)).build();
    }

    @Test
    public void testAddShop() throws Exception {

        String shopName = "Pizza Shop";
        Address address = new AddressBuilder().setNumber("10").setPostcode("SE16 7AJ").createAddress();

        AddShopRequest request = new AddShopRequest();
        request.setShopName(shopName);
        request.setAddress(address);

        String jsonRequest = getJsonRequest(request);

        mvc.perform(post("/shop/add").contentType(APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk());

        verify(addShopService, times(1)).save(shopName, address);

    }

    @Test
    public void testSearchShop() throws Exception {

        String shopName = "Pizza Shop";
        Address address = new AddressBuilder().setNumber("10").setPostcode("SE16 7AJ").createAddress();

        GeoLocation geoLocation = new GeoLocationBuilder().setLat(10.00909D).setLng(23.90900909).createGeoLocation();

        Shop shop = new ShopBuilder().setShopName("Pizza Shop").setAddress(address).setGeoLocation(geoLocation).createShop();

        when(nearestShopService.find(geoLocation)).thenReturn(
                new ShopBuilder().setShopName("Pizza Shop").setAddress(address).setGeoLocation(geoLocation).createShop()
        );

        mvc.perform(get("/shop/search")
                .param("latitude","10.00909")
                .param("longitude","23.90900909")
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(getJsonRequest(shop)));
    }

    private String getJsonRequest(Object request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(request);
    }

}


