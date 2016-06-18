package unit.service;

import com.db.test.bean.Address;
import com.db.test.bean.GeoLocation;
import com.db.test.bean.builder.AddressBuilder;
import com.db.test.bean.builder.ShopBuilder;
import com.db.test.repository.ShopRepository;
import com.db.test.service.AddShopService;
import com.db.test.service.GeoLocationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AddShopServiceTest {

    private AddShopService addShopService;
    @Mock
    GeoLocationService geoLocationService;

    @Mock
    ShopRepository shopRepository;

    @Before
    public void setUp() throws Exception {
        addShopService = new AddShopService(geoLocationService, shopRepository);
    }

    @Test
    public void testAddShop() {
        Address address = new AddressBuilder().setNumber("10").setPostcode("SE16 7AJ").createAddress();
        addShopService.save("ABC", address);

        verify(geoLocationService, times(1)).findLocation(address);

        verify(shopRepository, times(1)).addShop(new ShopBuilder()
                .setShopName("ABC")
                .setAddress(address)
                .setGeoLocation(any(GeoLocation.class))
                .createShop());

    }

}