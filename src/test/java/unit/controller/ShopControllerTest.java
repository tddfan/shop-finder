package unit.controller;

import com.db.test.bean.Address;
import com.db.test.controller.ShopController;
import com.db.test.request.AddShopRequest;
import com.db.test.service.AddShopService;
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

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class ShopControllerTest {

    private MockMvc mvc;

    @Mock
    private AddShopService addShopService;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new ShopController(addShopService)).build();
    }

    @Test
    public void testAddShop() throws Exception {

        String shopName = "Pizza Shop";
        Address address = new Address("10", "SE16 7AJ");

        AddShopRequest request = new AddShopRequest();
        request.setShopName(shopName);
        request.setAddress(address);

        String jsonRequest = getJsonRequest(request);

        mvc.perform(post("/shop/add").contentType(APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk());

    }


    private String getJsonRequest(Object request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(request);
    }

}


